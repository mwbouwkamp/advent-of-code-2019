package day17;

import utils.IntComputer;

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
        List<Cell> intersections =  picture.getCells().stream()
                .filter(c -> c.getCharacter() == '#')
                .filter(c -> c.getChildren().size() == 4)
                .collect(Collectors.toList());
        System.out.println(intersections.stream().map(Cell::getLocation).collect(Collectors.toList()).toString());
        return intersections;
    }

    public Picture getPicture() {
        return picture;
    }

}
