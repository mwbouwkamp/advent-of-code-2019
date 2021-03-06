package day2;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input2.txt").get(0);
        List<Integer> numbers = InputUtils.getNumbersFromCSVInput(input);

        //Day 1
        List<Integer> numbersInput = changeVerbNoun(numbers, 12, 2);
        List<Integer> numbersResult = new SolverDay2().intComputer(numbersInput);
        System.out.println("Solution 2.1: " + numbersResult.get(0));

        //Day 2
        int i = 0;
        int j = 0;
        OUTER:
        for (i = 0; i < 100; i++) {
            for (j = 0; j < 100; j++) {
                numbersInput = changeVerbNoun(numbers, i, j);
                numbersResult = new SolverDay2().intComputer(numbersInput);
                if (numbersResult.get(0) == 19690720) {
                    break OUTER;
                }
            }
        }
        System.out.println("Solution 2.2: " + (i * 100 + j));
    }

    private static List<Integer> changeVerbNoun(List<Integer> numbers, int noun, int verb) {
        List<Integer> numbersInput = new ArrayList<>(numbers);
        numbersInput.set(1, noun);
        numbersInput.set(2, verb);
        return numbersInput;
    }

}
