package day25;

import utils.FileUtils;
import utils.InputUtils;
import utils.IntComputer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class day25 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input25.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        new SolverDay25(numbers).solve();

    }

}
