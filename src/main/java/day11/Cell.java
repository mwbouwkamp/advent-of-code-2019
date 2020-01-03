package day11;

import java.awt.*;

public class Cell {
    private long color; //0 = black; 1 = white
    private Point location;
    private boolean painted;

    public Cell(Point location) {
        this.location = location;
        this.color = 0;
        this.painted = false;
    }

    public void setColor(long color) {
        this.color = color;
        painted = true;
    }

    public long getColor() {
        return color;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isPainted() {
        return painted;
    }

    @Override
    public String toString() {
        return location.toString() + color;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cell)) {
            return false;
        }
        Point otherCellLocation = ((Cell) other).location;
        return location.equals(otherCellLocation);
    }
}
