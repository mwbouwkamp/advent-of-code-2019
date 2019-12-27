package day20part2;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class SolverDay20Part2 {
    private Map<Point, Portal> portalLocations;
    private Map<String, Portal> portalMap;
    private List<Maze> mazeDimensions;

    private List<String> input, inputOuter, inputInner;

    public SolverDay20Part2(List<String> input) {
        this.input = input;
        this.inputOuter = new InputProcessor().processOuter(input);
        this.inputInner = new InputProcessor().processInner(input);

        Point start = findStartEndLocation(input, 'A');
        Point end = findStartEndLocation(input, 'Z');

        portalMap = getPortalMap(input);
        portalLocations = getPortalLocations();

        Maze outerMaze = new Maze(inputOuter, portalLocations, 0);
        Maze innerMaze = new Maze(inputInner, portalLocations, 1);

        outerMaze.setStart(start);
        outerMaze.setEnd(end);

        mazeDimensions = new ArrayList<>();
        mazeDimensions.add(outerMaze);
        mazeDimensions.add(innerMaze);

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

    private void printInput(List<String> input) {
        for (String s : input) {
            System.out.println(s);
        }
    }

    private Map<Point, Portal> getPortalLocations() {
        Map<Point, Portal> portalLocations = new HashMap<>();
        for (Portal portal: portalMap.values()) {
            portalLocations.put(portal.getToInner(), portal);
            portalLocations.put(portal.getToOuter(), portal);
        }
        return portalLocations;
    }

    private Map<String, Portal> getPortalMap(java.util.List<String> input) {
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
                            portal = new Portal("" + cell);
                        }
                        if (isInnerPortalEntrance(input, portalEntranceLocation)) {
                            portal.setToInner(portalEntranceLocation);
                        } else {
                            portal.setToOuter(portalEntranceLocation);
                        }
                        portals.put(portalString, portal);
                    }
                }
            }
        }
        portals.remove("AA");
        portals.remove("ZZ");
        return portals;
    }

    private boolean isInnerPortalEntrance(List<String> input, Point portalEntranceLocation) {
        return portalEntranceLocation.x != 2
                && portalEntranceLocation.x != input.get(portalEntranceLocation.y).length() - 3
                && portalEntranceLocation.y != 2
                && portalEntranceLocation.y != input.size() - 3;
    }

    public int solveDay() {
        PriorityQueue<State> fringe = new PriorityQueue<>();
        Maze outerMaze = mazeDimensions.get(0);
        fringe.add(new State(outerMaze.getStart(), 0));

        Map<MazeNode, Integer> history = new HashMap<>();

        MazeNode end = outerMaze.getEnd();
        int fastest = Integer.MAX_VALUE;

        while (fringe.size() > 0) {
            State toCheck = fringe.poll();
//            System.out.println("fringe size: " + fringe.size() + "; depth: " + toCheck.getNode().getDepth() + "; steps: " + toCheck.getSteps());
            history.put(toCheck.getNode(), toCheck.getSteps());
            if (toCheck.getNode() == end && toCheck.getSteps() < fastest) {
                fastest = toCheck.getSteps();
                int finalFastest = fastest;
                fringe = fringe.stream()
                        .filter(s -> s.getSteps() < finalFastest)
                        .collect(Collectors.toCollection(PriorityQueue::new));
                System.out.println("=======>>> " + fastest);
            } else {
                for (MazeNode child: toCheck.getNode().getChildren(input, mazeDimensions)) {
                    if (!history.containsKey(child)) {
                        if (toCheck.getSteps() < fastest) {
                            fringe.add(new State(child, toCheck.getSteps() + 1));
                        }
                    } else {
                        if (history.get(child) > toCheck.getSteps() + 1) {
                            fringe.add(new State(child, toCheck.getSteps() + 1));
                        }
                    }
                }
            }
        }
        return fastest;
    }


}
