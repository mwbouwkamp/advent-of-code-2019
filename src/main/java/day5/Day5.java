package day5;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input5.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        int inputCode = 5;
        System.out.println(new SolverDay5().solve(numbers, inputCode));
    }
}
