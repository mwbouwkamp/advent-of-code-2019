package day17;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Picture {
    private String stringRep;
    List<Cell> cells;

    public Picture(String stringRep) {
        this.stringRep = stringRep;
        this.cells = new ArrayList<>();
        String[] lines = stringRep.split("\n");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                if (lines[y].charAt(x) == '#' || lines[y].charAt(x) == '^') {
                    cells.add(new Cell(new Point(x, y), lines[y].charAt(x)));
                }
            }
        }
        for (Cell cell: cells) {
            cell.addChildren(cells);
        }
    }

    @Override
    public String toString() {
        return stringRep;
    }

    public List<Cell> getCells() {
        return cells;
    }
}
