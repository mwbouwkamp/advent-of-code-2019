package day17;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class Cell {
    private Point location;
    private char character;
    private List<Cell> children;

    public Cell(Point location, char character) {
        this.location = location;
        this.character = character;
    }

    public void addChildren(List<Cell> cells) {
        this.children = cells.stream()
                .filter(c -> c.isNeighBouring(location))
                .filter(c -> c.getCharacter() == '#')
                .collect(Collectors.toList());
    }

    public boolean isNeighBouring(Point point) {
        return (Math.abs(point.x - location.x) == 1 && point.y == location.y) || (Math.abs(point.y - location.y) == 1 && point.x == location.x);
    }

    public Point getLocation() {
        return location;
    }

    public List<Cell> getChildren() {
        return children;
    }

    public char getCharacter() {
        return character;
    }
}
