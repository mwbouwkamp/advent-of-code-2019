package day23;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day23 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input23.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        SolverDay23 solverDay23 = new SolverDay23(numbers);
        System.out.println(solverDay23.solveDay23_1());

    }
}
