package day15;

import utils.IntComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SurfaceSolverState {

    private final List<Long> numbers;
    private final List<Long> instructions;
    private final Long status;
    private final Point location;

    public SurfaceSolverState(List<Long> numbers) {
        this.numbers = numbers;
        this.instructions = new ArrayList<>();
        this.status = 1L;
        this.location = new Point(0, 0);
    }

    public SurfaceSolverState(SurfaceSolverState previousSurfaceSolverState, long newInstruction) {
        this.numbers = previousSurfaceSolverState.getNumbers();
        this.instructions = new ArrayList<>(previousSurfaceSolverState.getInstructions());
        this.instructions.add(newInstruction);
        this.status = determineStatus();
        this.location = this.status != 0
                ? determineLocation(previousSurfaceSolverState.getLocation(), newInstruction)
                : new Point(previousSurfaceSolverState.getLocation());
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

    public List<SurfaceSolverState> getChildren(List<SurfaceSolverState> history, int minSteps) {
        return IntStream.range(1, 5)
                .mapToObj(i -> new SurfaceSolverState(this, i))
                .filter(s -> s.lessSteps(minSteps))
                .filter(s -> s.notVisitedWithLessSteps(history))
                .collect(Collectors.toList());
    }

    public List<SurfaceSolverState> getChildren(List<SurfaceSolverState> history) {
        return IntStream.range(1, 5)
                .mapToObj(i -> new SurfaceSolverState(this, i))
                .filter(s -> s.notVisitedWithLessSteps(history))
                .collect(Collectors.toList());
    }

    public boolean notVisitedWithLessSteps(List<SurfaceSolverState> history) {
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
