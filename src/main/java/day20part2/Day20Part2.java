package day20part2;

import utils.FileUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day20Part2 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input20.txt");


        new SolverDay20Part2(input).solveDay();

    }

}
