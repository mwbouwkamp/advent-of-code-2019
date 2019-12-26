package day9;

import utils.InputUtils;
import utils.IntComputer;

import java.util.List;

public class SolverDay9 {

    List<Long> numbers;

    public SolverDay9(List<Long> numbers) {
        this.numbers = numbers;
    }

    public List<Long> solve() {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(1)
                .phaseSetting(1)
                .build();
        intComputer.start();
        while (!intComputer.hasTerminated()) {
            intComputer.runCycle(1);
        }
        return intComputer.getIntComputerOutput();
    }
}
