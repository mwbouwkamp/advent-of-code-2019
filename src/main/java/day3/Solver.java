package day3;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    public List<Point> extractCoordinates(List<String> directions) {
        List<Point> coordinates = new ArrayList<>();
        Point currentCoordinate = new Point(0, 0);
        for (String direction: directions) {
            char LRUD = direction.charAt(0);
            Integer steps = Integer.parseInt(direction.substring(1));
            switch (LRUD) {
                case 'L': //x-1
                    for (int i = 0; i < steps; i++) {
                        currentCoordinate = new Point(currentCoordinate.x - 1, currentCoordinate.y);
                        coordinates.add(currentCoordinate);
                    }
                    break;
                case 'R': //x+1
                    for (int i = 0; i < steps; i++) {
                        currentCoordinate = new Point(currentCoordinate.x + 1, currentCoordinate.y);
                        coordinates.add(currentCoordinate);
                    }
                    break;
                case 'U': //y+1
                    for (int i = 0; i < steps; i++) {
                        currentCoordinate = new Point(currentCoordinate.x, currentCoordinate.y + 1);
                        coordinates.add(currentCoordinate);
                    }
                    break;
                case 'D': //y-1
                    for (int i = 0; i < steps; i++) {
                        currentCoordinate = new Point(currentCoordinate.x, currentCoordinate.y - 1);
                        coordinates.add(currentCoordinate);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Direction not known: " + LRUD);
            }
        }
        return coordinates;
    }

    public List<Point> getPointsPresentInBothLists(List<Point> list1, List<Point> list2) {
        return list1.stream()
                .filter(s -> list2.contains(s))
                .collect(Collectors.toList());
    }

    public long getClosestCrossingManhattan(List<String> input1, List<String> input2) {
        List<Point> inBothLists = getIntersections(input1, input2);
        Point origin = new Point(0, 0);
        Point closest = inBothLists.stream()
                .reduce((a, b) -> getManHattan(a, origin) < getManHattan(b, origin) ? a : b)
                .orElse(origin);
        return getManHattan(closest, origin);
    }

    private List<Point> getIntersections(List<String> input1, List<String> input2) {
        List<Point> coordinates1 = extractCoordinates(input1);
        List<Point> coordinates2 = extractCoordinates(input2);
        return getPointsPresentInBothLists(coordinates1, coordinates2);
    }

    public long getClosestCrossingConnection(List<String> input1, List<String> input2) {
        List<Point> inBothLists = getIntersections(input1, input2);
        Point origin = new Point(0, 0);
        Map<Point, Long> connectionLengthsInput1 = getConnectionLength(input1, inBothLists);
        Map<Point, Long> connectionLengthsInput2 = getConnectionLength(input2, inBothLists);
        Map<Point, Long> connectionLengths = new HashMap<>();
        for (Point point: inBothLists) {
            connectionLengths.put(point, connectionLengthsInput1.get(point) + connectionLengthsInput2.get(point));
        }
        Point closestCrossingConnection = connectionLengths.keySet().stream()
                .reduce((a, b) -> connectionLengths.get(a) < connectionLengths.get(b) ? a : b)
                .orElse(origin);
        return connectionLengths.get(closestCrossingConnection);
    }

    public Map<Point, Long> getConnectionLength(List<String> directions, List<Point> points) {
        Point currentCoordinate = new Point (0, 0);
        Map<Point, Long> connectionLengths = new HashMap<>();
        long connectionLength = 0;
        for (String direction: directions) {
            char LRUD = direction.charAt(0);
            Integer steps = Integer.parseInt(direction.substring(1));
            switch (LRUD) {
                case 'L':
                    for (int i = 0; i < steps; i++) {
                        connectionLength++;
                        currentCoordinate = new Point(currentCoordinate.x - 1, currentCoordinate.y);
                        if (points.contains(currentCoordinate)) {
                            connectionLengths.put(currentCoordinate, connectionLength);
                        }
                    }
                    break;
                case 'R':
                    for (int i = 0; i < steps; i++) {
                        connectionLength++;
                        currentCoordinate = new Point(currentCoordinate.x + 1, currentCoordinate.y);
                        if (points.contains(currentCoordinate)) {
                            connectionLengths.put(currentCoordinate, connectionLength);
                        }
                    }
                    break;
                case 'U':
                    for (int i = 0; i < steps; i++) {
                        connectionLength++;
                        currentCoordinate = new Point(currentCoordinate.x, currentCoordinate.y + 1);
                        if (points.contains(currentCoordinate)) {
                            connectionLengths.put(currentCoordinate, connectionLength);
                        }
                    }
                    break;
                case 'D':
                    for (int i = 0; i < steps; i++) {
                        connectionLength++;
                        currentCoordinate = new Point(currentCoordinate.x, currentCoordinate.y - 1);
                        if (points.contains(currentCoordinate)) {
                            connectionLengths.put(currentCoordinate, connectionLength);
                        }
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Direction not known: " + LRUD);
            }
        }
        return connectionLengths;
    }

    public long getManHattan(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }
}
