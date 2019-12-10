package day9;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input9.txt").get(0);
        List<Integer> numbers = InputUtils.getNumbersFromCSVInput(input);

        System.out.println("Solution 9.1 " + new SolverDay9(numbers).solve().toString());





    }
}
