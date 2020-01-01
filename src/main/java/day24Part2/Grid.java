package day24Part2;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {
    private Map<Point, Cell> grid;
    private int dimension, width, height;
    private Point middle;

    public Grid(List<String> input, int dimension) {
        this.dimension = dimension;
        this.width = input.get(0).length();
        this.height = input.size();
        this.middle = new Point(width / 2, height / 2);
        populateGrid(input);
    }

    public Grid(Map<Point, Cell> grid, int dimension, int width, int height) {
        this.grid = grid;
        this.dimension = dimension;
        this.width = width;
        this.height = height;
        this.middle = new Point(width / 2, height / 2);
    }

    public void makeExternalConnections(Dimensions dimensions) {
        makeExternalConnections("OUTER", dimensions);
        makeExternalConnections("INNER", dimensions);
    }

    public Grid process() {
        Map<Point, Cell> newGrid = new HashMap<>();
        for (Point point: grid.keySet()) {
            newGrid.put(point, grid.get(point).process());
        }
        return new Grid(newGrid, dimension, width, height);
    }

    private void populateGrid(List<String> input) {
        this.grid = new HashMap<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y != middle.y || x != middle.x) {
                    grid.put(new Point(x, y), new Cell(input.get(y).charAt(x), new Point(x, y), dimension));
                }
            }
        }
    }

    public void makeInternalConnections() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y != middle.y || x != middle.x) {
                    Point toProcess = new Point(x, y);
                    if (y != 0 && !toProcess.equals(new Point(middle.x, middle.y + 1))) {
                        grid.get(toProcess).addChild("N", grid.get(new Point(toProcess.x, toProcess.y - 1)));
                    }
                    if (y != height - 1 && !toProcess.equals(new Point(middle.x, middle.y - 1))) {
                        grid.get(toProcess).addChild("S", grid.get(new Point(toProcess.x, toProcess.y + 1)));
                    }
                    if (x != 0 && !toProcess.equals(new Point(middle.x + 1, middle.y))) {
                        grid.get(toProcess).addChild("W", grid.get(new Point(toProcess.x - 1, toProcess.y)));
                    }
                    if (x != width - 1 && !toProcess.equals(new Point(middle.x - 1, middle.y))) {
                        grid.get(toProcess).addChild("E", grid.get(new Point(toProcess.x + 1, toProcess.y)));
                    }
                }
            }
        }
    }

    public void makeExternalConnections(String mode, Dimensions dimensions) {
        if (borderContainsBugs(mode)) {
            int otherDimension = mode.equals("OUTER")
                    ? dimension - 1
                    : dimension + 1;
            Grid outer = mode.equals("OUTER")
                    ? dimensions.getDimensionsMap().get(otherDimension)
                    : this;
            Grid inner = mode.equals("OUTER")
                    ? this
                    : dimensions.getDimensionsMap().get(otherDimension);

            Cell outerCell = outer.getGrid().get(new Point(1, 2));
            for (int y = 0; y < width; y++) {
                Cell innerCell = inner.getGrid().get(new Point(0, y));
                connectWE(outerCell, innerCell);
            }

            outerCell = outer.getGrid().get(new Point(3, 2));
            for (int y = 0; y < width; y++) {
                Cell innerCell = inner.getGrid().get(new Point(width - 1, y));
                connectWE(innerCell, outerCell);
            }

            outerCell = outer.getGrid().get(new Point(2, 1));
            for (int x = 0; x < height; x++) {
                Cell innerCell = inner.getGrid().get(new Point(x, 0));
                connectNS(outerCell, innerCell);
            }

            outerCell = outer.getGrid().get(new Point(2, 3));
            for (int x = 0; x < height; x++) {
                Cell cellThisDimension = inner.getGrid().get(new Point(x, height - 1));
                connectNS(cellThisDimension, outerCell);
            }
        }
    }

    public boolean borderContainsBugs(String mode) {
        long borderBugs = mode.equals("OUTER")
                ? IntStream.range(0, width)
                            .map(i -> grid.get(new Point(i, 0)).getContents())
                            .filter(c -> c == '#')
                            .count()
                + IntStream.range(0, width)
                            .map(i -> grid.get(new Point(i, height - 1)).getContents())
                            .filter(c -> c == '#')
                            .count()
                + IntStream.range(0, height)
                            .map(i -> grid.get(new Point(0, i)).getContents())
                            .filter(c -> c == '#')
                            .count()
                + IntStream.range(0, height)
                            .map(i -> grid.get(new Point(width - 1, i)).getContents())
                            .filter(c -> c == '#')
                            .count()
                : IntStream.range(middle.x - 1, middle.x + 1)
                            .map(i -> grid.get(new Point(i, middle.y - 1)).getContents())
                            .filter(c -> c == '#')
                            .count()
                + IntStream.range(middle.x - 1, middle.x + 1)
                            .map(i -> grid.get(new Point(i, middle.y + 1)).getContents())
                            .filter(c -> c == '#')
                            .count()
                + IntStream.range(middle.y - 1, middle.y + 1)
                            .map(i -> grid.get(new Point(middle.x - 1, i)).getContents())
                            .count()
                + IntStream.range(middle.y - 1, middle.y + 1)
                            .map(i -> grid.get(new Point(middle.x + 1, i)).getContents())
                            .count();
        return borderBugs > 0;
    }

    private void connectNS(Cell north, Cell south) {
        north.addChild("S", south);
        south.addChild("N", north);
    }

    private void connectWE(Cell west, Cell east) {
        west.addChild("E", east);
        east.addChild("W", west);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getMiddle() {
        return middle;
    }
    public Map<Point, Cell> getGrid() {
        return grid;
    }

    public long CountBugs() {
        return grid.values().stream()
                .map(Cell::getContents)
                .filter(v -> v == '#')
                .count();
    }

    public int getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y != middle.y || x != middle.x) {
                    builder.append(grid.get(new Point(x, y)));
                } else {
                    builder.append("?");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
