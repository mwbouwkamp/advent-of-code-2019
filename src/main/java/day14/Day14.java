package day14;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        List<Reaction> reactions = FileUtils.getStringsFromInput("input14.txt").stream()
                .map(s -> new Reaction(s))
                .collect(Collectors.toList());
        SolverDay14 solverDay14 = new SolverDay14(reactions);
        System.out.println("Solution 14.1: " + solverDay14.solve());
    }

}
