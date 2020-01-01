package day24Part1;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Day24 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input24.txt");
        Grid grid = new Grid(input);
        Set<Grid> history = new TreeSet<>();
        while (!history.contains(grid)) {
            history.add(grid);
            grid = grid.getChild();
        }
        System.out.println(grid.getValue());
    }

}
