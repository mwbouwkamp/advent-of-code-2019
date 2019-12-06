package day5;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input5.txt").get(0);
        List<Integer> numbers = InputUtils.getNumbersFromCSVInput(input);

        int inputCode = 5;
        List<Integer> numbersResult = new SolverDay5().intComputer(numbers, inputCode);
    }
}
