package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntComputer extends Thread {

    private Map<Long, Long> numbers;
    long maxPosition;
    private long inputCode;

    private boolean usesPhaseSetting;
    private long phaseSetting;
    private boolean usesPhaseSetting2;
    private long phaseSetting2;

    private Long relativeBase;
    private boolean haltable;
    private String name;

    List<Long> intComputerOutput;

    private boolean running;
    private boolean phaseSettingUsed;
    private boolean phaseSettingUsed2;
    private boolean terminated;
    private boolean active;

    public IntComputer(Map<Long, Long> numbers, long inputCode, long phaseSetting, boolean haltable, String name) {
        this.numbers = numbers;
        this.maxPosition = numbers.keySet().size();
        this.inputCode = inputCode;
        this.usesPhaseSetting = true;
        this.phaseSetting = phaseSetting;
        this.relativeBase = 0L;
        this.haltable = haltable;
        this.name = name;
        this.intComputerOutput = new ArrayList<>();
        phaseSettingUsed = false;
        this.running = false;
        this.active = true;
    }

    public IntComputer(Map<Long, Long> numbers, long inputCode, long phaseSetting, long phaseSetting2, boolean haltable, String name) {
        this.numbers = numbers;
        this.maxPosition = numbers.keySet().size();
        this.inputCode = inputCode;
        this.usesPhaseSetting = true;
        this.phaseSetting = phaseSetting;
        this.usesPhaseSetting2 = true;
        this.phaseSetting2 = phaseSetting2;
        this.relativeBase = 0L;
        this.haltable = haltable;
        this.name = name;
        this.intComputerOutput = new ArrayList<>();
        phaseSettingUsed = false;
        this.running = false;
        this.active = true;
    }

    public IntComputer(Map<Long, Long> numbers, long inputCode, boolean haltable, String name) {
        this.numbers = numbers;
        this.maxPosition = numbers.keySet().size();
        this.inputCode = inputCode;
        this.usesPhaseSetting = false;
        this.relativeBase = 0L;
        this.haltable = haltable;
        this.name = name;
        this.intComputerOutput = new ArrayList<>();
        phaseSettingUsed = false;
        this.running = false;
        this.active = true;
    }

    public static class IntComputerBuilder {
        private Map<Long, Long> numbers;
        private long inputCode = 0;
        private boolean usesPhaseSetting = false;
        private long phaseSetting = 0;
        private boolean usesPhaseSetting2 = false;
        private long phaseSetting2 = 0;
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
            this.usesPhaseSetting = true;
            return this;
        }

        public IntComputerBuilder phaseSetting2(long phaseSetting) {
            this.phaseSetting2 = phaseSetting;
            this.usesPhaseSetting2 = true;
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
            if (usesPhaseSetting & usesPhaseSetting2) {
                return new IntComputer(numbers, inputCode, phaseSetting, phaseSetting2, haltable, name);
            } else if (usesPhaseSetting) {
                return new IntComputer(numbers, inputCode, phaseSetting, haltable, name);
            } else {
                return new IntComputer(numbers, inputCode, haltable, name);
            }
        }

    }
    @Override
    public void run() {
        long pointer = 0;
        while (active) {
            if (running) {
                OpCode opCode = new OpCode(numbers.get(pointer));
                int opCodeInstution = Math.toIntExact(opCode.getInstruction());
                boolean dontMovePointerAtEnd = false;
                switch (opCodeInstution) {
                    case 1:
                        long firstNum = getNum(++pointer, opCode.getMode1());
                        long secondNum = getNum(++pointer, opCode.getMode2());
                        setNum(++pointer, opCode.getMode3(), firstNum + secondNum);
                        break;
                    case 2:
                        firstNum = getNum(++pointer, opCode.getMode1());
                        secondNum = getNum(++pointer, opCode.getMode2());
                        setNum(++pointer, opCode.getMode3(), firstNum * secondNum);
                        break;
                    case 3:
                        long toUse = inputCode;
                        if (usesPhaseSetting && !phaseSettingUsed) {
                            toUse = phaseSetting;
                            phaseSettingUsed = true;
                        } else {
                            if (usesPhaseSetting2 && !phaseSettingUsed2) {
                                toUse = phaseSetting2;
                                phaseSettingUsed2 = true;
                            }
                        }
                        setNum(++pointer, opCode.getMode1(), toUse);
                        break;
                    case 4:
                        long num = getNum(++pointer, opCode.getMode1());
                        intComputerOutput.add(num);
                        if (haltable) {
                            haltRunning();
                        }
                        synchronized (this) {
                            notify();
                        }
                        break;
                    case 5:
                        firstNum = getNum(++pointer, opCode.getMode1());
                        secondNum = getNum(++pointer, opCode.getMode2());
                        if (firstNum != 0) {
                            pointer = secondNum;
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 6:
                        firstNum = getNum(++pointer, opCode.getMode1());
                        secondNum = getNum(++pointer, opCode.getMode2());
                        if (firstNum == 0) {
                            pointer = secondNum;
                            dontMovePointerAtEnd = true;
                        }
                        break;
                    case 7:
                        firstNum = getNum(++pointer, opCode.getMode1());
                        secondNum = getNum(++pointer, opCode.getMode2());
                        long toStore = firstNum < secondNum ? 1 : 0;
                        setNum(++pointer, opCode.getMode3(), toStore);
                        break;
                    case 8:
                        firstNum = getNum(++pointer, opCode.getMode1());
                        secondNum = getNum(++pointer, opCode.getMode2());
                        toStore = firstNum == secondNum ? 1 : 0;
                        setNum(++pointer, opCode.getMode3(), toStore);
                        break;
                    case 9:
                        num = getNum(++pointer, opCode.getMode1());
                        relativeBase += num;
                        break;
                    case 99:
                        terminated = true;
                        synchronized (this) {
                            notify();
                        }
                        return;
                    default:
                        throw new IllegalArgumentException("Opcode not allowed: " + opCode.getInstruction());
                }
                if (!dontMovePointerAtEnd) {
                    pointer++;
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

    private void setNum(long pointer, int mode, long result) {
        switch (mode) {
            case 0:
                numbers.put(numbers.get(pointer), result);
                break;
            case 1:
                numbers.put(pointer, result);
                break;
            case 2:
                numbers.put(numbers.get(pointer) + relativeBase, result);
                break;
            default:
                throw new IllegalArgumentException("mode not supported: " + mode);
        }
    }

    private long getNum(Long pointer, int mode) {
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
                    numbers.put(numbers.get(pointer) + relativeBase, 0L);
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

    public void kill() {
        this.active = false;
    }

    public void receivePacket(Long x, Long y) {
        running = false;
        inputCode = -1;
        phaseSetting = x;
        phaseSetting2 = y;
        phaseSettingUsed2 = false;
        phaseSettingUsed = false;
        running = true;
    }

}
