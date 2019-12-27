package day22;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> instructions = FileUtils.getStringsFromInput("input22.txt");
        SolverDay22 solver = new SolverDay22(10007);
        solver.process(instructions);
        System.out.println(solver.getPosition(2019));



    }
}
