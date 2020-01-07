package day15;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FillingSolver {

    private int time;

    public FillingSolver() {
        this.time = 0;
    }

    public int solve(Point locationStart, List<Point> allLocations) {
        Point startLocation = allLocations.stream()
                .filter(l -> l.equals(locationStart))
                .findFirst()
                .orElse(null);
        List<Point> visitedLocations = new ArrayList<>();
        visitedLocations.add(startLocation);
        visitChildren(startLocation, allLocations, visitedLocations, 0);
        return time;
    }

    private void visitChildren(Point location, List<Point> allLocations, List<Point> visitedLocations, int path) {
        time = Math.max(path, time);
        int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] delta: deltas) {
            Point child = new Point(location.x - delta[0], location.y - delta[1]);
            if (allLocations.contains(child) && !visitedLocations.contains(child)) {
                visitedLocations.add(child);
                visitChildren(child, allLocations, visitedLocations, path + 1);
            }
        }
    }
}
