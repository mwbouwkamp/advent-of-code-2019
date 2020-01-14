package day19;

import utils.IntComputer;

import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Thread.sleep;

public class SolverDay19 {

    private List<Long> numbers;

    public SolverDay19(List<Long> numbers) {
        this.numbers = numbers;
    }

    public Point day19_2() {
        int y = findFirstRowWith100();
        int x = findFirstColWith1(y, 0);
        Point result = new Point(x, y);
        boolean shipFound = shipFound(result);
        while (!shipFound) {
            int inRow = inRow(result);
            int inColumn = inColumn(result);
            if (inRow < 100) {
                result = new Point(result.x, result.y + 1);
            } else if (inColumn < 100) {
                result = new Point (result.x + 1, result.y);
            }
            System.out.println(result + "; inRow: " + inRow + "; inColumn " + inColumn);
            shipFound = shipFound(result);
        }
        return result;
    }

    private int inColumn(Point point) {
        return IntStream.range(0, 100)
                .map(i -> (int) getResult(point.x, point.y + i))
                .sum();
    }

    private int inRow(Point point) {
        return IntStream.range(0, 100)
                .map(i -> (int) getResult(point.x + i, point.y))
                .sum();
    }

    private boolean shipFound(Point point) {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (getResult(point.x + x, point.y + y) == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    private int findFirstRowWith100() {
        int y = 100;
        int x = 0;
        while (true) {
            x = findFirstColWith1(y, x);
            if (getResult(x + 100, y) == 1) {
                return y;
            }
            y++;
        }
    }

    private int findFirstColWith1(int y, int prevX) {
        int x = prevX;
        while (true) {
            if (getResult(x, y) == 1) {
                return x;
            }
            x++;
        }
    }

    public int day19_1() {
        int numAffected = 0;
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                long result = getResult(x, y);
                if (result == 1) {
                    numAffected++;
                    System.out.print("#");
                }
                else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
        return numAffected;
    }

    private long getResult(int x, int y) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(x)
                .phaseSetting(y)
                .build();
        intComputer.start();
        intComputer.setRunning();
        while (!intComputer.hasTerminated()) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return intComputer.getIntComputerOutput().get(0);
    }

}
