package day20part2;


import java.awt.*;
import java.util.*;
import java.util.List;

public class MazeNode {
    private int depth;
    private Point location;
    private Set<MazeNode> children;
    private List<Portal> portals;

    public MazeNode(int depth, Point location) {
        this.depth = depth;
        this.location = location;
        this.children = new HashSet<>();
        this.portals = new ArrayList<>();
    }

    public Set<MazeNode> getChildren(List<String> input, List<Maze> mazeDimensions) {
        if (portals.size() > 0) {
            for (Portal portal: portals) {
//                System.out.println(portal.portalName);
                if (portal.getToInner().equals(location)) {
                    if (isInnerMazeNode(input) && depth + 1 >= mazeDimensions.size()) {
                        mazeDimensions.add(mazeDimensions.get(depth).createNewMaze(input, depth + 1));
                    }
                    children.add(mazeDimensions.get(depth + 1).getMazeNode(portal.getToOuter()));
                } else {
                    children.add(mazeDimensions.get(depth - 1).getMazeNode(portal.getToInner()));
                }
            }
        }
        return children;
    }

    private boolean isInnerMazeNode(List<String> input) {
        return location.x > 2
                || location.x < input.get(location.y).length() - 2
                || location.y > 2
                || location.y < input.size() - 2;
    }

    public void addChildren(List<String> input, Map<Point, MazeNode> nodeLocations) {
        int[][] deltas = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            int x = location.x + delta[0];
            int y = location.y + delta[1];
            char cell = input.get(y).charAt(x);
            if (cell == '.') {
                addChild(nodeLocations.get(new Point(x, y)));
            }
        }
    }

    public void addChild(MazeNode child) {
        children.add(child);
    }

    public void addPortal(Portal portal) {
        portals.add(portal);
    }

    public Point getLocation() {
        return location;
    }

    public int getDepth() {
        return depth;
    }

}
