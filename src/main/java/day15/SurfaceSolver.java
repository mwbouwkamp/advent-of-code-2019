package day15;

import java.util.ArrayList;
import java.util.List;

public class SurfaceSolver {

    private List<SurfaceSolverState> surfaceSolverStates;
    private SurfaceSolverState bestSurfaceSolverState;

    public SurfaceSolver() {
        this.surfaceSolverStates = new ArrayList<>();
    }

    public List<SurfaceSolverState> getSurfaceSolverStates() {
        return surfaceSolverStates;
    }

    public SurfaceSolverState getBestSurfaceSolverState() {
        return bestSurfaceSolverState;
    }

    public void solve(List<Long> numbers, int puzzle) {
        SurfaceSolverState startingSurfaceSolverState = new SurfaceSolverState(numbers);
        List<SurfaceSolverState> fringe = new ArrayList<>();
        List<SurfaceSolverState> history = new ArrayList<>();
        fringe.add(startingSurfaceSolverState);
        while (fringe.size() > 0) {
            SurfaceSolverState current = fringe.remove(0);
            history.add(current);
            surfaceSolverStates.add(current);
            if (current.getStatus() == 2) {
                if (null == bestSurfaceSolverState || current.getNumSteps() < bestSurfaceSolverState.getNumSteps()) {
                    bestSurfaceSolverState = current;
                    System.out.println("Current fastest: " + bestSurfaceSolverState.getNumSteps());
                }
            } else {
                if (puzzle == 1) {
                    fringe.addAll(current.getChildren(history, bestSurfaceSolverState.getNumSteps()));
                } else if (puzzle == 2) {
                    List<SurfaceSolverState> children = current.getChildren(history);
                    fringe.addAll(children);
                }
            }
        }
    }
}
