package day18;

import java.awt.*;
import java.util.*;
import java.util.List;

public class State implements Comparable<State> {

    private Tunnels tunnels;
    private List<Point> positions;
    private Set<Character> keys;
    private int numSteps;

    public State(Tunnels tunnels, List<Point> positions, Set<Character> keys, int numSteps) {
        this.tunnels = tunnels;
        this.positions = positions;
        this.keys = keys;
        this.numSteps = numSteps;
    }

    public List<State> getChildren() {
        List<State> children = new ArrayList<>();
        for (int id = 0; id < positions.size(); id++) {
            int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] delta: deltas) {
                State child = getchild(id, delta[0], delta[1]);
                if (null != child) {
                    children.add(child);
                }
            }
        }
        return children;
    }

    private State getchild(int id, int x, int y) {
        Point newPosition = new Point(positions.get(id).x + x, positions.get(id).y + y);
        List<Point> newPositions = new ArrayList<>(positions);
        newPositions.set(id, newPosition);
        char grid = tunnels.get(newPosition);
        if (grid == '#') {
            return null;
        }
        if (grid == '.') {
            return new State(tunnels, newPositions, keys, numSteps + 1);
        }
        if (Character.isUpperCase(grid)) {
            if (keys.contains(Character.toLowerCase(grid))) {
                return new State(tunnels, newPositions, keys, numSteps + 1);
            } else {
                return null;
            }
        }
        if (Character.isLowerCase(grid)) {
            Set newKeys = new TreeSet<>(keys);
            newKeys.add(grid);
            return new State(tunnels, newPositions, newKeys, numSteps + 1);
        }
        return null;
    }

    public int fastestPossibleRouteToKeysNotFound() {
        return tunnels.getKeys().keySet().stream()
                .filter(key -> !keys.contains(key))
                .mapToInt(k -> fastestPossibleRouteForKey(tunnels.getKeys().get(k)))
                .sum();
    }

    public int fastestPossibleRouteToClosestKeyNotFound() {
        return tunnels.getKeys().keySet().stream()
                .filter(key -> !keys.contains(key))
                .mapToInt(k -> fastestPossibleRouteForKey(tunnels.getKeys().get(k)))
                .min()
                .orElse(0);
    }

    public int fastestPossibleRouteForKey(Point keyPosition) {
        return positions.stream()
                .mapToInt(p -> getManHattan(p, keyPosition))
                .min()
                .orElse(0);
    }

    public int getManHattan(Point start, Point end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    @Override
    public int compareTo(State state) {
        if (compareToKeysFound(state) != 0) {
            return compareToKeysFound(state);
        } else if (compareToFastestPossibleRouteToClosestKeyNotFound(state) != 0) {
            return compareToFastestPossibleRouteToClosestKeyNotFound(state);
        }
        else {
            return -1 * (state.numSteps - this.numSteps);
        }
//        return 0;
    }

    private int compareToKeysFound(State state) {
        return state.keys.size() - this.keys.size();
    }

    private int compareToFastestPossibleRouteToClosestKeyNotFound(State state) {
//        return this.fastestPossibleRouteToClosestKeyNotFound() - state.fastestPossibleRouteToClosestKeyNotFound();
//        return this.fastestPossibleRouteToKeysNotFound() - state.fastestPossibleRouteToClosestKeyNotFound();
        return this.fastestPossibleRouteToKeysNotFound() + this.fastestPossibleRouteToClosestKeyNotFound()
                - state.fastestPossibleRouteToClosestKeyNotFound() + state.fastestPossibleRouteToClosestKeyNotFound();
    }

    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (Point position: positions) {
            toReturn += position.x + "," + position.y + ";";
        }
        return toReturn + keys.toString();
    }

    public int getNumKeys() {
        return keys.size();
    }

    public int getNumSteps() {
        return numSteps;
    }

    public int getTotalKeys() {
        return tunnels.getNumKeys();
    }
}
