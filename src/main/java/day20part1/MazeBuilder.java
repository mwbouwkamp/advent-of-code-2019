package day20part1;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeBuilder {
    MazeNode start;
    MazeNode end;

    Map<Point, MazeNode> nodeLocations;
    Map<String, Portal> portals;

    public MazeBuilder(List<String> input) {
        nodeLocations = getNodeLocations(input);
        portals = getPortals(input);

        setupBasicNodeChildren(input);
        setupPortalChildren();

        Point startLocation = findStartEndLocation(input, 'A');
        this.start = nodeLocations.get(startLocation);
        Point endLocation = findStartEndLocation(input, 'Z');
        this.end = nodeLocations.get(endLocation);

        System.out.println("End of input processing");
    }

    public Maze build() {
        return new Maze(start, end);
    }

    private Point findStartEndLocation(List<String> input, char startEndChar) {
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == startEndChar) {
                    Point location = new Point(x, y);
                    if (new NeighbourExplorer().getNeighboursBasedOnCharacter(input, location, startEndChar).size() > 0) {
                        List<Point> mazeEntranceList = new NeighbourExplorer().getNeighboursBasedOnCharacter(input, location, '.');
                        if (mazeEntranceList.size() == 1) {
                            return mazeEntranceList.get(0);
                        }
                    }
                }
            }
        }
        return null;
    }

    private Map<Point, MazeNode> getNodeLocations(List<String> input) {
        Map<Point, MazeNode> nodeLocations = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char cell = input.get(y).charAt(x);
                if (cell == '.') {
                    Point location = new Point(x, y);
                    nodeLocations.put(location, new MazeNode(location));
                }
            }
        }
        return nodeLocations;
    }

    private Map<String, Portal> getPortals(List<String> input) {
        Map<String, Portal> portals = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char cell = input.get(y).charAt(x);
                if (Character.isUpperCase(cell)) {
                    Point location = new Point(x, y);
                    if (new NeighbourExplorer().getNeighboursBasedOnCharacter(input, location, '.').size() > 0) {
                        Point portalEntranceLocation = new NeighbourExplorer().getNeighboursBasedOnCharacter(input, location, '.').get(0);
                        Point portalPartnerLabelLocation = new NeighbourExplorer().getNeighboursBasedOnUpperCase(input, location).get(0);
                        char portalPartnerLabel = input.get(portalPartnerLabelLocation.y).charAt(portalPartnerLabelLocation.x);
                        StringBuilder portalStringBuilder = new StringBuilder();
                        if (cell < portalPartnerLabel) {
                            portalStringBuilder.append(cell).append(portalPartnerLabel);
                        } else {
                            portalStringBuilder.append(portalPartnerLabel).append(cell);
                        }
                        String portalString = portalStringBuilder.toString();
                        Portal portal = portals.get(portalString);
                        if (null == portal) {
                            portal = new Portal();
                            portal.setMazeNodeIn(nodeLocations.get(portalEntranceLocation));
                            portals.put(portalString, portal);
                        } else {
                            portal.setMazeNodeOut(nodeLocations.get(portalEntranceLocation));
                        }
                    }
                }
            }
        }
        portals.remove("AA");
        portals.remove("ZZ");
        return portals;
    }

    private void setupBasicNodeChildren(List<String> input) {
        for (Point nodeLocation: nodeLocations.keySet()) {
            nodeLocations.get(nodeLocation).addChildren(input, nodeLocation, nodeLocations);
        }
    }

    private void setupPortalChildren() {
        for (Portal portal: portals.values()) {
            MazeNode in = portal.getMazeNodeIn();
            MazeNode out = portal.getMazeNodeOut();
            in.addChild(out);
            out.addChild(in);
        }

    }

    public MazeNode getStart() {
        return start;
    }

    public MazeNode getEnd() {
        return end;
    }
}
