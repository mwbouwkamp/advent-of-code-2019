package day17;

import utils.FileUtils;
import utils.InputUtils;
import utils.IntComputer;

import java.io.FileNotFoundException;
import java.util.List;

import static java.lang.Thread.sleep;

public class Day17 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input17.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);
        SolverDay17 solverDay17 = new SolverDay17(numbers);
        System.out.println(solverDay17.getNumIntersections());
        System.out.println(solverDay17.getSumAllignmentParameters());

        input = FileUtils.getStringsFromInput("input17_2.txt").get(0);
        numbers = InputUtils.getLongsFromCSVInput(input);
        solverDay17 = new SolverDay17(numbers);
        System.out.println(solverDay17.getPicture());
        System.out.println(solverDay17.instructionsToVisitAll().toString());




    }

}
