package day24Part1;

import java.util.List;

public class Grid implements Comparable<Grid> {
    String[][] grid;

    public Grid(List<String> input) {
        this.grid = new String[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            this.grid[i] = input.get(i).split("");
        }
    }

    public Grid(String[][] input) {
        this.grid = input;
    }

    public Grid getChild() {
        String[][] child = new String[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("#")) {
                    child[i][j] = getNumNeighbours(i, j) == 1
                            ? "#"
                            : ".";
                } else {
                    child[i][j] = getNumNeighbours(i, j) == 1 || getNumNeighbours(i, j) == 2
                            ? "#"
                            : ".";
                }
            }
        }
        return new Grid(child);
    }

    private int getNumNeighbours(int i, int j) {
        int numNeighbours = 0;
        if (i > 0 && grid[i - 1][j].equals("#")) {
            numNeighbours++;
        }
        if (i < grid.length - 1 && grid[i + 1][j].equals("#")) {
            numNeighbours++;
        }
        if (j > 0 && grid[i][j - 1].equals("#")) {
            numNeighbours++;
        }
        if (j < grid[0].length - 1 && grid[i][j + 1].equals("#")) {
            numNeighbours++;
        }
        return numNeighbours;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                builder.append(grid[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public long getValue() {
        long value = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].equals("#")) {
                    value += Math.pow(2, i * grid[0].length + j);
                }
            }
        }
        return value;
    }

    @Override
    public int compareTo(Grid o) {
        return (int) getValue() - (int) o.getValue();
    }
}
