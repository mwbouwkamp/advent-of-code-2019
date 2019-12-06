package day5;

import java.util.List;

public class SolverDay5 {

    public List<Integer> intComputer(List<Integer> numbers, int inputCode) {
        int pointer = 0;
        OUTER:
        while(true) {
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
                    numbers.set(numbers.get(pointer), inputCode);
                    break;
                case 4:
                    pointer = movePosition(pointer, numbers, 1);
                    System.out.println(numbers.get(numbers.get(pointer)));
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
//                    else {
//                        pointer = movePosition(pointer, numbers, 1);
//                    }
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
//                    else {
//                        pointer = movePosition(pointer, numbers, 1);
//                    }
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
                    break OUTER;
                default:
                    throw new IllegalArgumentException("Opcode not allowed: " + opCode.getInstruction());
            }
            if (!dontMovePointerAtEnd) {
                pointer = movePosition(pointer, numbers, 1);
            }
        }
        return numbers;
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

}
