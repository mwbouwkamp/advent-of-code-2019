package day15;

import utils.IntComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class State {

    private final List<Long> numbers;
    private final List<Long> instructions;
    private final Long status;
    private final Point location;

    public State(List<Long> numbers) {
        this.numbers = numbers;
        this.instructions = new ArrayList<>();
        this.status = 1L;
        this.location = new Point(0, 0);
    }

    public State(State previousState, long newInstruction) {
        this.numbers = previousState.getNumbers();
        this.instructions = new ArrayList<>(previousState.getInstructions());
        this.instructions.add(newInstruction);
        this.status = determineStatus();
        this.location = this.status != 0
                ? determineLocation(previousState.getLocation(), newInstruction)
                : new Point(previousState.getLocation());
    }

    public Point determineLocation(Point location, long instruction) {
        switch ((int) instruction) {
            case 1:
                return new Point(location.x, location.y - 1);
            case 2:
                return new Point(location.x, location.y + 1);
            case 3:
                return new Point(location.x - 1, location.y);
            case 4:
                return new Point(location.x + 1, location.y);
            default:
                throw new IllegalArgumentException("Instruction not allowed: " + instruction);
        }
    }

    public Point getLocation() {
        return location;
    }

    private long determineStatus() {
        IntComputer intComputer = getNewIntComputer(numbers);
        long status = 0L;
        for (int i = 0; i < instructions.size(); i++) {
            long instruction = instructions.get(i);
            status = intComputer.runCycle(instruction);
            if (status == 2) {
                System.out.println("Status == 2");
            }
        }
        intComputer.kill();
        return status;
    }

    private IntComputer getNewIntComputer(List<Long> numbers) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .isHaltable()
                .build();
        intComputer.start();
        return intComputer;
    }

    public List<State> getChildren(List<State> history, int minSteps) {
        List<State> children = new ArrayList<>();
        for (long i = 1; i <= 4; i++) {
            children.add(new State(this, i));
        }
        children = children.stream()
                .filter(s -> s.lessSteps(minSteps))
                .filter(s -> s.notVisitedWithLessSteps(history))
                .collect(Collectors.toList());
        return children;
    }

    public boolean notVisitedWithLessSteps(List<State> history) {
        int stepsHistory = history.stream()
                .filter(s -> s.getLocation().equals(location))
                .mapToInt(s -> s.getNumSteps())
                .min()
                .orElse(Integer.MAX_VALUE);
        return getNumSteps() < stepsHistory;
    }

    public int getNumSteps() {
        return instructions.size();
    }

    public boolean lessSteps(int numSteps) {
        return instructions.size() < numSteps;
    }

    public List<Long> getInstructions() {
        return instructions;
    }

    public List<Long> getNumbers() {
        return numbers;
    }

    public long getStatus() {
        return status;
    }
}
