package day1;

import java.io.FileNotFoundException;
import java.util.List;
import utils.FileUtils;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        List<Long> numbers = FileUtils.getLongsFromInput("input1.txt");
        Solver solver = new Solver();
        System.out.println("Solution 1.1: " + solver.calculateTotalFuel(numbers));
        System.out.println("Solution 1.2: " + solver.calculateTotalFuelIncludingFuelForFuelTransport(numbers));
    }
}
