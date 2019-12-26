package day18;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day18 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input18.txt");
        Tunnels tunnels = new Tunnels(input);
        try {
            tunnels.adjustTunnelsForSecondPart();
        } catch (Exception e) {
            e.printStackTrace();
        }

        State solution = new State(tunnels, tunnels.getStart(), new HashSet<>(), 3856);

        Queue<State> fringe = new PriorityQueue<>();
        Map<String, State> visited = new HashMap<>();

        State toCheck = new State(tunnels, tunnels.getStart(), new HashSet<>(), 0);
        fringe.add(toCheck);
        int maxKeysFound = 0;
        while (fringe.size() > 0) {
            toCheck = fringe.poll();
            visited.put(toCheck.toString(), toCheck);
            if (toCheck.getNumKeys() == toCheck.getTotalKeys()) {
                if (solution.getNumSteps() > toCheck.getNumSteps()) {
                    solution = toCheck;
                    System.out.println(solution.getNumSteps());
                    State finalSolution1 = solution;
                    fringe = fringe.stream()
                            .filter(state -> state.getNumSteps() + state.fastestPossibleRouteToKeysNotFound() < finalSolution1.getNumSteps())
                            .collect(Collectors.toCollection(PriorityQueue::new));
                }
            } else {
                List<State> children = toCheck.getChildren();
                if (toCheck.getNumKeys() > maxKeysFound) {
                    maxKeysFound = toCheck.getNumKeys();
                    System.out.println(toCheck + "     " + toCheck.getNumSteps() + "/" + solution.getNumSteps());
                }
                State finalSolution = solution;
                children = children.stream()
                        .filter(child -> notExceedingStepsBestSolution(finalSolution, child))
                        .filter(child -> notYetVisited(visited, child))
                        .collect(Collectors.toList());
                fringe.addAll(children);
            }
        }
        System.out.println(solution.getNumSteps());
    }

    private static boolean notYetVisited(Map<String, State> visited, State child) {
        if (visited.containsKey(child.toString())) {
            return child.getNumSteps() < visited.get(child.toString()).getNumSteps();
        } else {
            return true;
        }
    }

    private static boolean notExceedingStepsBestSolution(State bestSolution, State child) {
        return child.getNumSteps() + child.fastestPossibleRouteToKeysNotFound() < bestSolution.getNumSteps();
    }
}
