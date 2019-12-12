package day10;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SolverDay10 {

    public int getMaxVisible(List<Asteroid> asteroids) {
        return getBestAsteroid(asteroids).countVisible(asteroids);
    }

    public Asteroid getBestAsteroid(List<Asteroid> asteroids) {
        return asteroids.stream()
                .reduce((a, b) -> a.countVisible(asteroids) > b.countVisible(asteroids) ? a : b)
                .orElse(null);
    }

    public Asteroid get200th(Asteroid origin, List<Asteroid> asteroids) {
        Map<Double, List<Asteroid>> asteroidMap = new TreeMap<>();
        for (Asteroid asteroid: asteroids) {
            if (asteroid != origin) {
                double angle = asteroid.getAngle(origin);
                if (asteroidMap.containsKey(angle)) {
                    asteroidMap.get(angle).add(asteroid);
                } else {
                    List<Asteroid> newSet = new ArrayList<>();
                    newSet.add(asteroid);
                    asteroidMap.put(angle, newSet);
                }
            }
        }
        Asteroid result = null;
        List<Double> keys = new ArrayList<>(asteroidMap.keySet());
        for (int i = 0; i < 200; i++) {
            List<Asteroid> asteroidsWithAngle = asteroidMap.get(keys.get(i % keys.size()));
            Asteroid asteroidToRemove = asteroidsWithAngle.stream()
                    .reduce((a, b) -> a.getDistance(origin) < b.getDistance(origin) ? a : b)
                    .orElse(null);
            result = asteroidToRemove;
            System.out.println(i + "  " + result.getLocation());
            asteroidsWithAngle.remove(asteroidToRemove);
            if (asteroidsWithAngle.size() == 0) {
                asteroidMap.remove(keys.get(i % keys.size()));
            }
        }
        return result;
    }

//    public Asteroid get200th(List<Asteroid> asteroids) {
//        Asteroid center = getBestAsteroid(asteroids);
//        Map<Double, Asteroid> onPostiveYAxis = asteroids.stream()
//                .map(a -> new Asteroid.LineOfSight(a.getLineOfSightString(a, 2), a))
//                .filter(a -> a.quadrant == "YAXSPLUS")
//                .collect(Collectors.toMap(a -> a.xyRatio, Function.identity()));
//    }

}
