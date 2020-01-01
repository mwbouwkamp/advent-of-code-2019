package day24Part2;

import java.awt.*;
import java.util.*;

public class Cell {
    private Point location;
    private int dimension;
    private Map<String, Set<Cell>> children;
    private char contents;

    public Cell(char contents, Point location, int dimension) {
        this.location = location;
        this.dimension = dimension;
        this.children = new HashMap<>();
        this.children.put("N", new HashSet<>());
        this.children.put("S", new HashSet<>());
        this.children.put("W", new HashSet<>());
        this.children.put("E", new HashSet<>());
        this.contents = contents;
    }

    public Cell(Cell cell) {
        this.children = new HashMap<>();
        this.children.put("N", new HashSet<>());
        this.children.put("S", new HashSet<>());
        this.children.put("W", new HashSet<>());
        this.children.put("E", new HashSet<>());
        this.contents = cell.getContents();
        this.location = cell.getLocation();
        this.dimension = cell.getDimension();
    }

    public Cell process() {
        long numBugNeighbours = getNumNeighbourBugs();
        char newContents;
        if (contents == '#') {
            newContents = numBugNeighbours == 1
                    ? '#'
                    :'.';
        } else {
            newContents = numBugNeighbours == 1 || numBugNeighbours == 2
                    ? '#'
                    : '.';
        }
        return new Cell(newContents, new Point(location), dimension);
    }

    public void addChild(String direction, Cell child) {
        children.get(direction).add(child);
    }

    public char getContents() {
        return contents;
    }

    public Map<String, Set<Cell>> getChildren() {
        return children;
    }

//    @Override
//    public String toString() {
//        return contents + " (location: " + location.x + "," + location.y + "; dimension: " + dimension + ")";
//    }

    @Override
    public String toString() {
        return contents + "";
    }

    public long getNumNeighbourBugs() {
        return children.values().stream()
                .flatMap(Set::stream)
                .filter(c -> c.getContents() == '#')
                .count();
    }

    public Cell getCopy(){
        return new Cell(this);
    }

    public Point getLocation() {
        return location;
    }

    public int getDimension() {
        return dimension;
    }
}
