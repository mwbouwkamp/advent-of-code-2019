package utils;

import day5.OpCode;

import java.util.ArrayList;
import java.util.List;

public class IntComputer extends Thread {

    private List<Integer> numbers;
    private int inputCode;
    private int phaseSetting;
    private boolean haltable;
    private String name;

    List<Integer> intComputerOutput;

    private boolean running;
    private boolean phaseSettingUsed;

    public IntComputer(List<Integer> numbers, int inputCode, int phaseSetting, boolean haltable, String name) {
        this.numbers = new ArrayList<>(numbers);
        this.inputCode = inputCode;
        this.phaseSetting = phaseSetting;
        this.haltable = haltable;
        this.name = name;
        this.intComputerOutput = new ArrayList<>();
        phaseSettingUsed = false;
        this.running = false;
    }

    public static class IntComputerBuilder {
        private List<Integer> numbers;
        private int inputCode = 0;
        private int phaseSetting = 0;
        private boolean haltable = false;
        private String name = "";

        public IntComputerBuilder(List<Integer> numbers) {
            this.numbers = numbers;
        }

        public IntComputerBuilder inputCode(int inputCode) {
            this.inputCode = inputCode;
            return this;
        }

        public IntComputerBuilder phaseSetting(int phaseSetting) {
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
            while (running) {
                while(running)
                {
                    System.out.println(name + " " + phaseSetting + " " + inputCode);
                    OpCode opCode = new OpCode(numbers.get(pointer));
                    boolean dontMovePointerAtEnd = false;
                    switch (opCode.getInstruction()) {
                        case 1:
                            pointer = movePosition(pointer, numbers, 1);
                            int firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                            pointer = movePosition(pointer, numbers, 1);
                            int secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                            pointer = movePosition(pointer, numbers, 1);
                            if ((opCode.getModeResult() == 0)) {
                                numbers.set(numbers.get(pointer), firstNum + secondNum);
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
                                numbers.set(numbers.get(pointer), firstNum * secondNum);
                            } else {
                                numbers.set(pointer, firstNum * secondNum);
                            }
                            break;
                        case 3:
                            pointer = movePosition(pointer, numbers, 1);
                            if (phaseSettingUsed) {
                                numbers.set(numbers.get(pointer), inputCode);
                            }
                            else {
                                numbers.set(numbers.get(pointer), phaseSetting);
                                phaseSettingUsed = true;
                            }
                            break;
                        case 4:
                            if (name.equals("A")) {
                                System.out.println();
                            }
                            pointer = movePosition(pointer, numbers, 1);
                            intComputerOutput.add(numbers.get(numbers.get(pointer)));
                            if (intComputerOutput.size() > 0) {
                                System.out.println(intComputerOutput.toString());
                            }
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
                                pointer = secondNum;
                                dontMovePointerAtEnd = true;
                            }
                            break;
                        case 6:
                            pointer = movePosition(pointer, numbers, 1);
                            firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                            pointer = movePosition(pointer, numbers, 1);
                            secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                            if (firstNum == 0) {
                                pointer = secondNum;
                                dontMovePointerAtEnd = true;
                            }
                            break;
                        case 7:
                            pointer = movePosition(pointer, numbers, 1);
                            firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                            pointer = movePosition(pointer, numbers, 1);
                            secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                            pointer = movePosition(pointer, numbers, 1);
                            int toStore = firstNum < secondNum ? 1 : 0;
                            numbers.set(numbers.get(pointer), toStore);
                            break;
                        case 8:
                            pointer = movePosition(pointer, numbers, 1);
                            firstNum = getNum(numbers, pointer, opCode.getModeInput1());
                            pointer = movePosition(pointer, numbers, 1);
                            secondNum = getNum(numbers, pointer, opCode.getModeInput2());
                            pointer = movePosition(pointer, numbers, 1);
                            toStore = firstNum == secondNum ? 1 : 0;
                            numbers.set(numbers.get(pointer), toStore);
                            break;
                        case 99:
//                            System.out.println("IntComputer terminated: " + name);
                            return;
                        default:
                            throw new IllegalArgumentException("Opcode not allowed: " + opCode.getInstruction());
                    }
                    if (!dontMovePointerAtEnd) {
                        pointer = movePosition(pointer, numbers, 1);
                    }
                }
            }
        }
    }

    private int movePosition(int pointer, List<Integer> integerList, int steps) {
        pointer += steps;
        return pointer  % integerList.size();
    }

    private int getNum(List<Integer> numbers, int pointer, int mode) {
        return (mode == 0)
                ? numbers.get(numbers.get(pointer))
                : numbers.get(pointer);
    }

    public List<Integer> getIntComputerOutput() {
        return intComputerOutput;
    }

    public void setRunning() {
        running = true;
    }

    public void haltRunning() {
        running = false;
    }

    public void setInputCode(int inputCode) {
        this.inputCode = inputCode;
    }

    public boolean isRunning() {
        return running;
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

    public int runCycle(int resultE) {
        setInputCode(resultE);
        setRunning();
        waitForIntcomputer();
        return getIntComputerOutput().get(getIntComputerOutput().size() - 1);
    }


}
