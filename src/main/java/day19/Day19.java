package day19;

import utils.FileUtils;
import utils.InputUtils;
import utils.IntComputer;

import java.io.FileNotFoundException;
import java.util.List;

import static java.lang.Thread.sleep;

public class Day19 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input19.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

//        System.out.println(new SolverDay19(numbers).day19_1());
        System.out.println(new SolverDay19(numbers).day19_2());
    }

}
