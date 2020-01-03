package day11;

import utils.IntComputer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SolverDay11 {

    public Map<Point, Cell> paintHull(List<Long> numbers, long input) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(input)
                .phaseSetting(input)
                .isHaltable()
                .build();
        intComputer.start();
        Map<Point, Cell> cells = new HashMap<>();
        Point position = new Point(0, 0);
        char[] directions = new char[] {'u', 'r', 'd', 'l'};
        int direction = 0;
        while(!intComputer.hasTerminated()) {
            long result = intComputer.runCycle(input);
            Cell cellToAdd = new Cell(position);
//            if (position.equals(new Point(-16, 57))) {
//                System.out.println();
//            }
            cellToAdd.setColor(result);
            cells.put(position, cellToAdd);
            result = intComputer.runCycle(input);
            if (result == 0) {
                direction = direction == 0
                        ? directions.length - 1
                        : direction - 1;
            } else {
                direction = direction == directions.length - 1
                        ? 0
                        : direction + 1;
            }
            switch (directions[direction]) {
                case 'u':
                    position = new Point(position.x, position.y - 1);
                    break;
                case 'r':
                    position = new Point(position.x + 1, position.y);
                    break;
                case 'd':
                    position = new Point(position.x, position.y + 1);
                    break;
                case 'l':
                    position = new Point(position.x - 1, position.y);
                    break;
                default:
                    throw new IllegalArgumentException("Direction not allowed: " + directions[direction]);
            }
            input = cells.containsKey(position)
                    ? cells.get(position).getColor()
                    : 0;
        }
        return cells;
    }


//    public List<Long> getPaintInstructions(List<Long> numbers) {
//        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
//                .inputCode(0)
//                .phaseSetting(0)
//                .build();
//        intComputer.start();
//        intComputer.runCycle(0);
//        while (!intComputer.hasTerminated()) {
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return intComputer.getIntComputerOutput();
//    }
//
//    public List<Cell> paint(List<Long> paintInstructions) {
//        List<Cell> cells = new ArrayList<>();
//        Set<Point> points = new HashSet<>();
//        Point position = new Point(0, 0);
//        char[] directions = new char[] {'u', 'r', 'd', 'l'};
//        int direction = 0;
//        for (int i = 0; i < paintInstructions.size(); i = i + 2) {
//            Cell cell = new Cell(position);
//            points.add(position);
//            cell.setColor(Math.toIntExact(paintInstructions.get(i)));
////            if (!cells.contains(cell)) {
//                cells.add(cell);
////            }
//            if (paintInstructions.get(i + 1) == 0) {
//                direction = direction == 0
//                        ? directions.length - 1
//                        : direction - 1;
//            } else {
//                direction = direction == directions.length - 1
//                        ? 0
//                        : direction + 1;
//            }
//            switch (directions[direction]) {
//                case 'u':
//                    position = new Point(position.x, position.y - 1);
//                    break;
//                case 'r':
//                    position = new Point(position.x + 1, position.y);
//                    break;
//                case 'd':
//                    position = new Point(position.x, position.y + 1);
//                    break;
//                case 'l':
//                    position = new Point(position.x - 1, position.y);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Direction not allowed: " + directions[direction]);
//            }
//        }
//        System.out.println(points.size());
//        return cells;
//    }

    public long getPaintedCells(Map<Point, Cell> cells) {
        return cells.values().stream()
                .filter(Cell::isPainted)
                .count();
    }
}
