package day20part1;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day20Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> input = FileUtils.getStringsFromInput("input20.txt");
        Maze maze = new MazeBuilder(input).build();

        solveDay1(maze);


    }

    private static int solveDay1(Maze maze) {
        PriorityQueue<State> fringe = new PriorityQueue<>();
        fringe.add(new State(maze.getStart(), 0));

        Map<MazeNode, Integer> history = new HashMap<>();

        MazeNode end = maze.getEnd();
        int fastest = Integer.MAX_VALUE;

        while (fringe.size() > 0) {
            State toCheck = fringe.poll();
            history.put(toCheck.getNode(), toCheck.getSteps());
            if (toCheck.getNode() == end && toCheck.getSteps() < fastest) {
                fastest = toCheck.getSteps();
                int finalFastest = fastest;
                fringe = fringe.stream()
                        .filter(s -> s.getSteps() < finalFastest)
                        .collect(Collectors.toCollection(PriorityQueue::new));
                System.out.println(fastest);
            } else {
                for (MazeNode child: toCheck.getNode().getChildren()) {
                    if (!history.containsKey(child)) {
                        fringe.add(new State(child, toCheck.getSteps() +1));
                    } else {
                        if (history.get(child) > toCheck.getSteps() + 1) {
                            fringe.add(new State(child, toCheck.getSteps() +1));
                        }
                    }
                }
            }
        }
        return fastest;
    }
}
