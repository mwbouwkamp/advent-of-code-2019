package day15;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input15.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        System.out.println("Solution: " + new SolverDay15().getNumStepsShortest(numbers));

    }
}
