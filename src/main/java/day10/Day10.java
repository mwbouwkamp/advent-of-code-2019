package day10;

import utils.FileUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input10.txt");
        List<Asteroid> asteroids = getAsteroids(input);
        Asteroid bestAsteroid = new SolverDay10().getBestAsteroid(asteroids);
        System.out.println("Solution 10.1: " + new SolverDay10().getMaxVisible(asteroids));
        System.out.println(bestAsteroid.getLocation().toString());

        Asteroid last = new SolverDay10().get200th(bestAsteroid, asteroids);
        System.out.println("Solution 10.2: " + (last.getLocation().x * 100 + last.getLocation().y));

    }

    private static List<Asteroid> getAsteroids(List<String> input) {
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (input.get(i).charAt(j) == '#') {
                    asteroids.add(new Asteroid(new Point(j, i)));
                }
            }
        }
        return asteroids;
    }
}
