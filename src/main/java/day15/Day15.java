package day15;

import utils.FileUtils;
import utils.InputUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input15.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        SurfaceSolver surfaceSolver = new SurfaceSolver();
//        surfaceSolver.solve(numbers, 1);
//        int result1 = surfaceSolver.getBestSurfaceSolverState().getNumSteps();
//        System.out.println("Solution 15.1: " + result1);

        surfaceSolver = new SurfaceSolver();
        surfaceSolver.solve(numbers, 2);
        List<Point> locations = surfaceSolver.getSurfaceSolverStates().stream()
                .map(s -> s.getLocation())
                .collect(Collectors.toList());
        Point locationStart = surfaceSolver.getSurfaceSolverStates().stream()
                .filter(s -> s.getStatus() == 2)
                .map(s -> s.getLocation())
                .findFirst()
                .orElse(null);
        System.out.println(locations.toString());
        System.out.println(locationStart);
        FillingSolver fillingSolver = new FillingSolver();
        int time = fillingSolver.solve(locationStart, locations);
        System.out.println("Solution 15.2: " + time);




    }
}
