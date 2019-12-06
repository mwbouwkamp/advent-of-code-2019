package day5;

import day2.SolverDay2;
import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringFromInput("input5.txt").get(0);
        List<Integer> numbers = InputUtils.getNumbersFromCSVInput(input);

        int inputCode = 5;
        List<Integer> numbersResult = new SolverDay5().intComputer(numbers, inputCode);
    }
}
