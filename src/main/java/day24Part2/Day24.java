package day24Part2;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day24 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input24.txt");
        Dimensions dimensions = new Dimensions(input);
        for (int i = 0; i < 200; i++) {
            dimensions = dimensions.process();
        }
        System.out.println(dimensions.countBugs());

    }

}
