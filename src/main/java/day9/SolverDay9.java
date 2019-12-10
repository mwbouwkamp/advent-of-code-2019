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
        String input = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
        numbers = InputUtils.getNumbersFromCSVInput(input);

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
