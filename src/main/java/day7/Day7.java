package day7;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input7.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

//        System.out.println("Solution 7.1: " + new SolverDay7(numbers, 5, 0).getMaxOutput());

        System.out.println("Solution 7.2: " + new SolverDay7(numbers, 5, 5).getMaxOutputWithFeedback());

    }

}
