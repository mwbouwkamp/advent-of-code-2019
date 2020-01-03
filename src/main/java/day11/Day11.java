package day11;

import utils.FileUtils;
import utils.InputUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input11.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);


        SolverDay11 solver = new SolverDay11();

        Map<Point, Cell> cells = solver.paintHull(numbers, 0);
        System.out.println(cells.size());

        cells = solver.paintHull(numbers, 1);
        Point minPoint = cells.keySet().stream().parallel()
                .reduce((a, b) -> getCorner(a, b, "MIN"))
                .orElse(null);
        Point maxPoint = cells.keySet().stream().parallel()
                .reduce((a, b) -> getCorner(a, b, "MAX"))
                .orElse(null);
        System.out.println(minPoint);
        System.out.println(maxPoint);
        long[][] grid = new long[maxPoint.y - minPoint.y + 1][maxPoint.x - minPoint.x + 1];
        for (Cell cell: cells.values()) {
            grid[cell.getLocation().y - minPoint.y][cell.getLocation().x - minPoint.x] = cell.getColor();
        }
        StringBuilder builder = new StringBuilder();
        for (long[] line: grid) {
            for(long cell: line) {
                builder.append(cell == 1 ? "#" : " ");
            }
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    public static Point getCorner(Point a, Point b, String mode) {
        Point toReturn = new Point(0, 0);
        toReturn.x = mode.equals("MIN")
                ? Math.min(a.x, b.x)
                : Math.max(a.x, b.x);
        toReturn.y = mode.equals("MIN")
                ? Math.min(a.y, b.y)
                : Math.max(a.y, b.y);
        return toReturn;
    }

}
