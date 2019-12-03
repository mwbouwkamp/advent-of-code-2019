package day3;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> numbers = FileUtils.getStringFromInput("input3.txt");
        List<String> input1 = InputUtils.getStringsFromCSVInput(numbers.get(0));
        List<String> input2 = InputUtils.getStringsFromCSVInput(numbers.get(1));

        long distanceManhattan = new Solver().getClosestCrossingManhattan(input1, input2);
        System.out.println("Solution 3.1: "  + distanceManhattan);

        long distanceConnection = new Solver().getClosestCrossingConnection(input1, input2);
        System.out.println("Solution 3.2: "  + distanceConnection);
    }
}
