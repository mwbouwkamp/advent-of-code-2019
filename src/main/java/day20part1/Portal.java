package day20part1;

public class Portal {
    private MazeNode mazeNodeIn;
    private MazeNode mazeNodeOut;

    public void setMazeNodeIn(MazeNode mazeNodeIn) {
        this.mazeNodeIn = mazeNodeIn;
    }

    public void setMazeNodeOut(MazeNode mazeNodeOut) {
        this.mazeNodeOut = mazeNodeOut;
    }

    public MazeNode getMazeNodeIn() {
        return mazeNodeIn;
    }

    public MazeNode getMazeNodeOut() {
        return mazeNodeOut;
    }
}
