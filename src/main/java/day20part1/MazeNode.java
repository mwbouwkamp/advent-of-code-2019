package day20part1;

import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeNode {
    Point location;
    Set<MazeNode> children;

    public MazeNode(Point location) {
        this.location = location;
        this.children = new HashSet<>();
    }

    public Set<MazeNode> getChildren() {
        return children;
    }

    public void addChildren(List<String> input, Point position, Map<Point, MazeNode> nodeLocations) {
        int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            int x = position.x + delta[0];
            int y = position.y + delta[1];
            char cell = input.get(y).charAt(x);
            if (cell == '.') {
                addChild(nodeLocations.get(new Point(x, y)));
            }
        }
    }

    public void addChild(MazeNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return location + " " + children.size();
    }
}
