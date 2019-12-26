package day20;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NeighbourExplorer {

    public List<Point> getNeighboursBasedOnCharacter(List<String> input, Point location, char character) {
        List<Point> neighboursWithCharacter = new ArrayList<>();
        int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            int xToCheck = location.x + delta[0];
            int yToCheck = location.y + delta[1];
            try {
                if (input.get(yToCheck).charAt(xToCheck) == character) {
                    neighboursWithCharacter.add(new Point(xToCheck, yToCheck));
                }
            } catch (Exception e) {
                System.out.println("nullPointer in getNeighboursBasedOnCharacter");
            }
        }
        return neighboursWithCharacter;
    }

    public List<Point> getNeighboursBasedOnUpperCase(List<String> input, Point location) {
        List<Point> neighboursWithCharacter = new ArrayList<>();
        int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            int xToCheck = location.x + delta[0];
            int yToCheck = location.y + delta[1];
            try {
                if (Character.isUpperCase(input.get(yToCheck).charAt(xToCheck))) {
                    neighboursWithCharacter.add(new Point(xToCheck, yToCheck));
                }
            } catch (Exception e) {
                System.out.println("nullPointer in getNeighboursBasedOnCharacter");
            }
        }
        return neighboursWithCharacter;
    }

}
