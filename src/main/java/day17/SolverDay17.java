package day17;

import utils.IntComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SolverDay17 {
    private Picture picture;

    public SolverDay17(List<Long> numbers) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
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
        List<Long> result = intComputer.getIntComputerOutput();
        String stringRep = result.stream()
                .map(l -> (char) Math.toIntExact(l))
                .map(l -> l + "")
                .reduce((a, b) -> a + b)
                .orElse(null);
        this.picture = new Picture(stringRep);
    }

    public int getNumIntersections() {
        return getIntersections().size();

    }

    public int getSumAllignmentParameters() {
        return getIntersections().stream()
                .mapToInt(c -> c.getLocation().x * c.getLocation().y)
                .sum();
    }

    public List<Cell> getIntersections() {
        return picture.getCells().stream()
                .filter(c -> c.getChildren().size() == 4)
                .collect(Collectors.toList());
    }

    public Picture getPicture() {
        return picture;
    }

    public List<List<Character>> instructionsToVisitAll() {
        List<List<Character>> instructionsToVisitAllList = new ArrayList<>();
        List<Cell> cells = picture.getCells();
        State state = new State(cells);
        List<State> fringe = new ArrayList<>();
        fringe.add(state);
        while (fringe.size() > 0) {
            State toCheck = fringe.remove(0);
            System.out.println(toCheck.getRemaining().size() + toCheck.getVisited().size());
            if (toCheck.getRemaining().size() == 0) {
                instructionsToVisitAllList.add(toCheck.getInstructions());
            } else {
                fringe.addAll(toCheck.getChildren());
            }
        }
        return instructionsToVisitAllList;
    }

    class State {
        List<Cell> visited;
        List<Cell> remaining;
        List<Character> instructions;
        char direction; //nswe

        State(List<Cell> cells) {
            this.visited = new ArrayList<>();
            Cell startingCell = cells.stream()
                    .filter(c -> c.getCharacter() == '^')
                    .findFirst()
                    .orElse(null);
            this.visited.add(startingCell);
            this.remaining = new ArrayList<>(cells);
            this.remaining.remove(startingCell);
            this.instructions = new ArrayList<>();
            this.direction = 'n';
        }

        State(State previousState, Cell newCell) {
            this.visited = new ArrayList<>(previousState.getVisited());
            this.visited.add(newCell);
            this.remaining = new ArrayList<>(previousState.getRemaining());
            this.remaining.remove(newCell);
            this.instructions = new ArrayList<>(previousState.getInstructions());
            char newDirection = getRelativeLocation(this.visited.get(this.visited.size() - 2), newCell);
            char newInstruction = determineNewInstruction(newDirection, previousState.getDirection());
            this.instructions.add(newInstruction);
            this.direction = newDirection;
        }

        private char determineNewInstruction(char newDirection, char oldDirection) {
            String directions = "nesw";
            int deltaDirection = directions.indexOf(oldDirection) - directions.indexOf(newDirection);
            if (deltaDirection == -4) {
                System.out.println();
            }
            switch (deltaDirection) {
                case -1:
                case 3:
                    return 'r';
                case 1:
                case -3:
                    return 'l';
                case 0:
                    return 's';
                default:
                    throw new IllegalArgumentException("deltaDirection not allowed: " + deltaDirection);
            }
        }

        private char getRelativeLocation(Cell current, Cell target) {
            char relLocation = 'x';
            int deltaX = target.getLocation().x - current.getLocation().x;
            int deltaY = target.getLocation().y - current.getLocation().y;
            if (deltaX == 0 && deltaY == 1) {
                relLocation = 's';
            } else if (deltaX == 0 && deltaY == -1) {
                relLocation = 'n';
            } else if (deltaX == 1 && deltaY == 0) {
                relLocation = 'e';
            } else if (deltaX == -1 && deltaY == 0) {
                relLocation = 'w';
            }
            return relLocation;
        }

        List<Cell> getVisited() {
            return visited;
        }

        List<Cell> getRemaining() {
            return remaining;
        }

        List<Character> getInstructions() {
            return instructions;
        }

        char getDirection() {
            return direction;
        }

        List<State> getChildren() {
            Cell current = visited.get(visited.size() - 1);
            List<Cell> childrenOfCurrent = current.getChildren();
            if (visited.size() > 1) {
                childrenOfCurrent.remove(visited.get(visited.size() - 2));
            }
            return childrenOfCurrent.stream()
                    .map(c -> new State(this, c))
                    .collect(Collectors.toList());
        }

        @Override
        public String toString() {
            String[] lines = picture.toString().split("\n");
            char[][] grid = new char[lines.length][lines[0].length()];
            for (int i = 0; i < visited.size(); i++) {
                Cell cell = visited.get(i);
                grid[cell.getLocation().y][cell.getLocation().x] = ((i % 10) + "").charAt(0);
            }
            for (Cell cell: remaining) {
                grid[cell.getLocation().y][cell.getLocation().x] = '#';
            }
            StringBuilder builder = new StringBuilder();
            for (char[] line: grid) {
                builder.append(line);
                builder.append("\n");
            }
            return builder.toString();
        }

    }


}
