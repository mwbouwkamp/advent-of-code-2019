package day15;

import java.util.ArrayList;
import java.util.List;

public class SolverDay15 {

    public int getNumStepsShortest(List<Long> numbers) {
        State startingState = new State(numbers);
        List<State> fringe = new ArrayList<>();
        List<State> history = new ArrayList<>();
        fringe.add(startingState);
        State fastestState = null;
        int fastest = Integer.MAX_VALUE;
        while (fringe.size() > 0) {
            State current = fringe.remove(0);
            history.add(current);
            if (current.getStatus() == 2) {
                if (current.getNumSteps() < fastest) {
                    fastest = current.getNumSteps();
                    fastestState = current;
                    System.out.println("Current fastest: " + fastest);
                }
            } else {
                fringe.addAll(current.getChildren(history, fastest));
            }
        }
        return fastest;
    }
}
