package day20part1;

public class Maze {
    private MazeNode start;
    private MazeNode end;

    public Maze(MazeNode start, MazeNode end) {
        this.start = start;
        this.end = end;
    }

    public MazeNode getStart() {
        return start;
    }

    public MazeNode getEnd() {
        return end;
    }


}
