package day20;

public class State implements Comparable<State> {
    MazeNode node;
    int steps;

    public State(MazeNode node, int steps) {
        this.node = node;
        this.steps = steps;
    }

    @Override
    public int compareTo(State otherState) {
        return otherState.steps - this.steps;
    }

    @Override
    public String toString() {
        return "[" + node + "]" + ": " + steps;
    }
    public MazeNode getNode() {
        return node;
    }

    public int getSteps() {
        return steps;
    }
}
