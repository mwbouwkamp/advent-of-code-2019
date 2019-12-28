package day22;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Day22 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> instructions = FileUtils.getStringsFromInput("input22.txt");
        SolverDay22 solver = new SolverDay22(10007);
//        solver.process(instructions);
//        System.out.println(solver.getPosition(2019));

        solver = new SolverDay22(119315717514047L);
        System.out.println(solver.solve(instructions, 2020, 0));

        solver = new SolverDay22(119315717514047L);
        System.out.println(solver.solve(instructions, 2020, 10006));

        solver = new SolverDay22(119315717514047L);
        System.out.println(solver.solve(instructions, 2020, 20012));

        solver = new SolverDay22(119315717514047L);
        System.out.println(solver.solve(instructions, 2020, 101741582076661L % 10006 - 1));  //4039, 8722 (low)



    }
}
