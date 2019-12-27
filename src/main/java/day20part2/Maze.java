package day20part2;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maze {
    private int depth;
    private MazeNode start, end;
    private Map<Point, MazeNode> nodeLocations;
    private Map<Point, Portal> portalLocations;

    public Maze(List<String> input, Map<Point, Portal> portalLocations, int depth) {
        this.portalLocations = portalLocations;
        this.depth = depth;

        this.nodeLocations = getNodeLocations(input);

        setupNodeChildren(input);
        setupNodePortals(input);
    }

    public Maze createNewMaze(List<String> input, int depth) {
        return new Maze(input, portalLocations, depth);
    }

    public MazeNode getMazeNode(Point location) {
        return nodeLocations.get(location);
    }

    private void setupNodePortals(List<String> input) {
        for (MazeNode mazeNode: nodeLocations.values()) {
            if (new NeighbourExplorer().getNeighboursBasedOnUpperCase(input, mazeNode.getLocation()).size() > 0) {
                Portal portal = portalLocations.get(mazeNode.getLocation());
                if (null != portal) {
                    mazeNode.addPortal(portal);
                }
            }
        }
    }

    private void setupNodeChildren(List<String> input) {
        for (MazeNode mazeNode: nodeLocations.values()) {
            mazeNode.addChildren(input, nodeLocations);
        }
    }

    
    private Map<Point, MazeNode> getNodeLocations(List<String> input) {
        Map<Point, MazeNode> nodeLocations = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char cell = input.get(y).charAt(x);
                if (cell == '.') {
                    Point location = new Point(x, y);
                    nodeLocations.put(location, new MazeNode(depth, location));
                }
            }
        }
        return nodeLocations;
    }

    public MazeNode getStart() {
        return start;
    }

    public MazeNode getEnd() {
        return end;
    }

    public void setStart(Point start) {
        this.start = nodeLocations.get(start);
    }

    public void setEnd(Point end) {
        this.end = nodeLocations.get(end);
    }
}
