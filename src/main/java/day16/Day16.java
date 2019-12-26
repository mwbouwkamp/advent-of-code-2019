package day16;

import utils.FileUtils;

import java.io.FileNotFoundException;

public class Day16 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input16.txt").get(0);

//        SolverDay16 solver = new SolverDay16(input, 100, 0);
//        System.out.println(solver.solve());

        StringBuilder newInputBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            newInputBuilder.append(input);
        }
        input = newInputBuilder.toString();
        int offset = Integer.parseInt(input.substring(0, 8));

        SolverDay16 solver = new SolverDay16(input, 100, offset);
        System.out.println(solver.solve());

    }
}
