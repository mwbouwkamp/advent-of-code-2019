package day9;

import utils.InputUtils;
import utils.IntComputer;

import java.util.List;

import static java.lang.Thread.sleep;

public class SolverDay9 {

    List<Long> numbers;

    public SolverDay9(List<Long> numbers) {
        this.numbers = numbers;
    }

    public List<Long> solve() {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(2)
                .phaseSetting(2)
                .build();
        intComputer.start();
        intComputer.runCycle(2);
        while (!intComputer.hasTerminated()) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return intComputer.getIntComputerOutput();
    }
}
