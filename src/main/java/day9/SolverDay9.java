package day9;

import utils.InputUtils;
import utils.IntComputer;

import java.util.List;

public class SolverDay9 {

    List<Integer> numbers;

    public SolverDay9(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> solve() {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(0)
                .build();
        intComputer.start();
        while (!intComputer.hasTerminated()) {
            intComputer.runCycle(1);
        }
        return intComputer.getIntComputerOutput();
    }
}
