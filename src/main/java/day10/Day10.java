package day10;

import utils.FileUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input10.txt");
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '#') {
                    asteroids.add(new Asteroid(new Point(j, i)));
                }
            }
        }
        int result = asteroids.stream()
                .mapToInt(a -> a.countVisible(asteroids))
                .max()
                .orElse(-1);
        System.out.println("Solution 10.1: " + result);

    }
}
