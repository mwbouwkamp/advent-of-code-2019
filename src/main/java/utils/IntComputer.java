package utils;

import java.util.ArrayList;
import java.util.List;

public class IntComputer extends Thread {

    private List<Long> numbers;
    private long inputCode;
    private long phaseSetting;
    private long relativeBase;
    private boolean haltable;
    private String name;

    List<Long> intComputerOutput;

    private boolean running;
    private boolean phaseSettingUsed;
    private boolean terminated;

    public IntComputer(List<Long> numbers, long inputCode, long phaseSetting, boolean haltable, String name) {
        this.numbers = new ArrayList<>(numbers);
        this.inputCode = inputCode;
        this.phaseSetting = phaseSetting;
        this.relativeBase = 0;
        this.haltable = haltable;
        this.name = name;
        this.intComputerOutput = new ArrayList<>();
        phaseSettingUsed = false;
        this.running = false;
    }

    public static class IntComputerBuilder {
        private List<Long> numbers;
        private long inputCode = 0;
        private long phaseSetting = 0;
        private boolean haltable = false;
        private String name = "";

        public IntComputerBuilder(List<Long> numbers) {
            this.numbers = numbers;
        }

        public IntComputerBuilder inputCode(long inputCode) {
            this.inputCode = inputCode;
            return this;
        }

        public IntComputerBuilder phaseSetting(long phaseSetting) {
            this.phaseSetting = phaseSetting;
            return this;
        }

        public IntComputerBuilder isHaltable() {
            this.haltable = true;
            return this;
        }

        public IntComputerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public IntComputer build() {
            return new IntComputer(numbers, inputCode, phaseSetting, haltable, name);
        }

    }
    @Override
    public void run() {
        int pointer = 0;
        while (true) {
            if (running)
            {
                OpCode opCode = new OpCode(numbers.get(pointer));
                int opCodeInstution = Math.toIntExact(opCode.getInstruction());
                boolean dontMovePointerAtEnd = false;
                switch (opCodeInstution) {
                    case 1:
                        pointer = movePosition(pointer, numbers, 1);
                        long firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        long secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer, numbers, 1);
                        if ((opCode.getModeResult() == 0)) {
                            numbers.set(Math.toIntExact(numbers.get(pointer)), firstNum + secondNum);
                        } else {
                            numbers.set(pointer, firstNum + secondNum);
                        }
                        break;
                    case 2:
                        pointer = movePosition(pointer, numbers, 1);
                        firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer, numbers, 1);
                        if ((opCode.getModeResult() == 0)) {
                            numbers.set(Math.toIntExact(numbers.get(pointer)), firstNum * secondNum);
                        } else {
                            numbers.set(pointer, firstNum * secondNum);
                        }
                        break;
                    case 3:
                        pointer = movePosition(pointer, numbers, 1);
                        if (phaseSettingUsed) {
                            numbers.set(Math.toIntExact(numbers.get(pointer)), inputCode);
                        }
                        else {
                            numbers.set(Math.toIntExact(numbers.get(pointer)), phaseSetting);
                            phaseSettingUsed = true;
                        }
                        break;
                    case 4:
                        pointer = movePosition(pointer, numbers, 1);
                        long num = getNum(numbers, pointer, opCode.getModeInput1());
                        intComputerOutput.add(num);
                        if (haltable) {
                            haltRunning();
                        }
                        synchronized (this) {
                            notify();
                        }
                        break;
                    case 5:
                        pointer = movePosition(pointer, numbers, 1);
                        firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        if (firstNum != 0) {
                            pointer = Math.toIntExact(secondNum);
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 6:
                        pointer = movePosition(pointer, numbers, 1);
                        firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        if (firstNum == 0) {
                            pointer = Math.toIntExact(secondNum);
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 7:
                        pointer = movePosition(pointer, numbers, 1);
                        firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer, numbers, 1);
                        long toStore = firstNum < secondNum ? 1 : 0;
                        numbers.set(Math.toIntExact(numbers.get(pointer)), toStore);
                        break;
                    case 8:
                        pointer = movePosition(pointer, numbers, 1);
                        firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer, numbers, 1);
                        secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer, numbers, 1);
                        toStore = firstNum == secondNum ? 1 : 0;
                        numbers.set(Math.toIntExact(numbers.get(pointer)), toStore);
                        break;
                    case 9:
                        pointer = movePosition(pointer, numbers, 1);
                        num = getNum(numbers, pointer, opCode.getModeInput1());
                        relativeBase += num;
                        break;
                    case 99:
                        terminated = true;
                        return;
                    default:
                        throw new IllegalArgumentException("Opcode not allowed: " + opCode.getInstruction());
                }
                if (!dontMovePointerAtEnd) {
                    pointer = movePosition(pointer, numbers, 1);
                }
            }
            else {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int movePosition(int pointer, List<Long> list, int steps) {
        pointer += steps;
        return pointer % list.size();
    }

    private long getNum(List<Long> numbers, int pointer, int mode) {
        long num;
        switch (mode) {
            case 0:
                num = numbers.get(Math.toIntExact(numbers.get(pointer)));
                break;
            case 1:
                num = numbers.get(pointer);
                break;
            case 2:
                num = numbers.get(Math.toIntExact(numbers.get(pointer) + relativeBase));
                break;
            default:
                throw new IllegalArgumentException("mode not supported: " + mode);
        }
        while (num > numbers.size()) {
            numbers.add(0L);
        }
        return num;
    }

    public List<Long> getIntComputerOutput() {
        return intComputerOutput;
    }

    public void setRunning() {
        running = true;
    }

    public void haltRunning() {
        running = false;
    }

    public void setInputCode(long inputCode) {
        this.inputCode = inputCode;
    }

    public void waitForIntcomputer() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long runCycle(long result) {
        setInputCode(result);
        setRunning();
        waitForIntcomputer();
        return getIntComputerOutput().get(getIntComputerOutput().size() - 1);
    }

    public boolean hasTerminated() {
        return terminated;
    }


}
