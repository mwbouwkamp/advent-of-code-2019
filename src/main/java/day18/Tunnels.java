package day18;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tunnels {
    private char[][] grid;
    private List<Point> start;
    private Map<Character, Point> keys;

    public Tunnels(List<String> input) {
        grid = new char[input.size()][];
        start = new ArrayList<>();
        keys = new TreeMap<>();
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '@') {
                    start.add(new Point(j, i));
                    grid[i][j] = '.';
                } else if (Character.isLowerCase(grid[i][j])) {
                    keys.put(grid[i][j], new Point(j, i));
                }
            }
        }
    }

    public void adjustTunnelsForSecondPart() throws Exception {
        if (start.size() > 1) {
            throw new Exception("tunnels have already been adjusted");
        }
        Point oldStart = new Point(start.get(0));
        start.remove(oldStart);
        grid[oldStart.x - 1][oldStart.y - 1] = '.';
        grid[oldStart.x][oldStart.y - 1] = '#';
        grid[oldStart.x + 1][oldStart.y - 1] = '.';
        grid[oldStart.x - 1][oldStart.y] = '#';
        grid[oldStart.x][oldStart.y] = '#';
        grid[oldStart.x + 1][oldStart.y] = '#';
        grid[oldStart.x - 1][oldStart.y + 1] = '.';
        grid[oldStart.x][oldStart.y  + 1] = '#';
        grid[oldStart.x + 1][oldStart.y + 1] = '.';
        start.add(new Point(oldStart.x - 1, oldStart.y - 1));
        start.add(new Point(oldStart.x + 1, oldStart.y - 1));
        start.add(new Point(oldStart.x - 1, oldStart.y + 1));
        start.add(new Point(oldStart.x + 1, oldStart.y + 1));
    }

    public char get(Point coord) {
        return grid[coord.y][coord.x];
    }

    public int getNumKeys() {
        return keys.size();
    }

    public List<Point> getStart() {
        return start;
    }

    public Map<Character, Point> getKeys() {
        return keys;
    }
}
