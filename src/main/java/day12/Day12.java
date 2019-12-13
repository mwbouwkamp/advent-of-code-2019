package day12;

import java.lang.reflect.Array;
import java.util.*;

public class Day12 {

    public static void main(String[] args) {
        List<Moon> moonList = new ArrayList<>();
        moonList.add(new Moon(5, 13, -3));
        moonList.add(new Moon(18, -7, 13));
        moonList.add(new Moon(16, 3, 4));
        moonList.add(new Moon(0, 8, 8));
//        moonList.add(new Moon(-1, 0, 2));
//        moonList.add(new Moon(2, -10, -7));
//        moonList.add(new Moon(4, -8, 8));
//        moonList.add(new Moon(3, 5, -1));
//        moonList.add(new Moon(-8, -10, 0));
//        moonList.add(new Moon(5, 5, 10));
//        moonList.add(new Moon(2, -7, 3));
//        moonList.add(new Moon(9, -8, -3));

        Set<String> positions = new TreeSet<>();
        for (int i = 0; i < 1000; i++) {
            for (Moon moon: moonList) {
                moon.updateVelocity(moonList);
            }
            for (Moon moon: moonList) {
                moon.updatePosition();
            }
        }
        for (Moon moon: moonList) {
            System.out.println(Arrays.toString(moon.getPosition()) + "   " + Arrays.toString(moon.getVelocity()));
        }
        int result = moonList.stream()
                .mapToInt(m -> m.calculatePotential() * m.calculateKinetic())
                .sum();
        System.out.println("Solution 12.1: " + result);
    }

}
