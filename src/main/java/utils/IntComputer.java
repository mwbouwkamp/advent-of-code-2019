package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntComputer extends Thread {

    private Map<Long, Long> numbers;
    long maxPosition;
    private long inputCode;
    private long phaseSetting;
    private long relativeBase;
    private boolean haltable;
    private String name;

    List<Long> intComputerOutput;

    private boolean running;
    private boolean phaseSettingUsed;
    private boolean terminated;

    public IntComputer(Map<Long, Long> numbers, long inputCode, long phaseSetting, boolean haltable, String name) {
        this.numbers = numbers;
        this.maxPosition = numbers.keySet().size();
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
        private Map<Long, Long> numbers;
        private long inputCode = 0;
        private long phaseSetting = 0;
        private boolean haltable = false;
        private String name = "";

        public IntComputerBuilder(List<Long> numbers) {
            this.numbers = new TreeMap<>();
            for (int i = 0; i < numbers.size(); i++) {
                this.numbers.put((long) i, numbers.get(i));
            }
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
        long pointer = 0;
        while (true) {
            if (running)
            {
                OpCode opCode = new OpCode(numbers.get(pointer));
                int opCodeInstution = Math.toIntExact(opCode.getInstruction());
                boolean dontMovePointerAtEnd = false;
                switch (opCodeInstution) {
                    case 1:
                        pointer = movePosition(pointer);
                        long firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        long secondNum = getNum(pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer);
                        if ((opCode.getModeResult() == 0)) {
                            numbers.put(numbers.get(pointer), firstNum + secondNum);
                        } else {
                            numbers.put(pointer, firstNum + secondNum);
                        }
                        break;
                    case 2:
                        pointer = movePosition(pointer);
                        firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        secondNum = getNum(pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer);
                        if ((opCode.getModeResult() == 0)) {
                            numbers.put(numbers.get(pointer), firstNum * secondNum);
                        } else {
                            numbers.put(pointer, firstNum * secondNum);
                        }
                        break;
                    case 3:
                        pointer = movePosition(pointer);
                        if (phaseSettingUsed) {
                            numbers.put(numbers.get(pointer), inputCode);
                        }
                        else {
                            numbers.put(numbers.get(pointer), phaseSetting);
                            phaseSettingUsed = true;
                        }
                        break;
                    case 4:
                        pointer = movePosition(pointer);
                        long num = getNum(pointer, opCode.getModeInput1());
                        intComputerOutput.add(num);
                        if (haltable) {
                            haltRunning();
                        }
                        synchronized (this) {
                            notify();
                        }
                        break;
                    case 5:
                        pointer = movePosition(pointer);
                        firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        secondNum = getNum(pointer, opCode.getModeInput2());
                        if (firstNum != 0) {
                            pointer = Math.toIntExact(secondNum);
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 6:
                        pointer = movePosition(pointer);
                        firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        secondNum = getNum(pointer, opCode.getModeInput2());
                        if (firstNum == 0) {
                            pointer = Math.toIntExact(secondNum);
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 7:
                        pointer = movePosition(pointer);
                        firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        secondNum = getNum(pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer);
                        long toStore = firstNum < secondNum ? 1 : 0;
                        numbers.put(numbers.get(pointer), toStore);
                        break;
                    case 8:
                        pointer = movePosition(pointer);
                        firstNum = getNum(pointer, opCode.getModeInput1());
                        pointer = movePosition(pointer);
                        secondNum = getNum(pointer, opCode.getModeInput2());
                        pointer = movePosition(pointer);
                        toStore = firstNum == secondNum ? 1 : 0;
                        numbers.put(numbers.get(pointer), toStore);
                        break;
                    case 9:
                        pointer = movePosition(pointer);
                        num = getNum(pointer, opCode.getModeInput1());
                        relativeBase += num;
                        break;
                    case 99:
                        terminated = true;
                        return;
                    default:
                        throw new IllegalArgumentException("Opcode not allowed: " + opCode.getInstruction());
                }
                if (!dontMovePointerAtEnd) {
                    pointer = movePosition(pointer);
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

    private long movePosition(long pointer) {
        System.out.println(pointer);
        return pointer > maxPosition ? 0 : ++pointer;
    }

    private long getNum(long pointer, int mode) {
        Long num;
        switch (mode) {
            case 0:
                num = numbers.get(numbers.get(pointer));
                if (null == num) {
                    num = 0L;
                    numbers.put(numbers.get(pointer), 0L);
                }
                break;
            case 1:
                num = numbers.get(pointer);
                break;
            case 2:
                num = numbers.get(numbers.get(pointer) + relativeBase);
                if (null == num) {
                    num = 0L;
                    numbers.put(numbers.get(pointer), 0L);
                }
                break;
            default:
                throw new IllegalArgumentException("mode not supported: " + mode);
        }
        if (num > numbers.size()) {
            maxPosition = num;
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
