package day2;

import java.util.List;

public class SolverDay2 {

    public List<Integer> processIntCode(List<Integer> numbers) {
        int opCode = 0;
        int pointer = 0;
        while(true) {
            opCode = numbers.get(pointer);
            pointer = movePosition(pointer, numbers, 1);
            int firstNum = numbers.get(pointer);
            pointer = movePosition(pointer, numbers, 1);
            int secondNum = numbers.get(pointer);
            pointer = movePosition(pointer, numbers, 1);
            if (opCode == 1) {
                numbers.set(numbers.get(pointer), numbers.get(firstNum) + numbers.get(secondNum));
            }
            else if (opCode == 2) {
                numbers.set(numbers.get(pointer), numbers.get(firstNum) * numbers.get(secondNum));
            }
            else if (opCode == 99) {
                break;
            }
            else {
                throw new IllegalArgumentException("Not a valid list of numbers");
            }
            pointer = movePosition(pointer, numbers, 1);
        }
        return numbers;
    }

    private int movePosition(int pointer, List<Integer> integerList, int steps) {
        pointer += steps;
        return pointer  % integerList.size();
    }
}
