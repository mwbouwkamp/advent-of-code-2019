package day5;

import utils.IntComputer;

import java.util.List;

public class SolverDay5 {

    public int solve(List<Integer> numbers, int inputCode) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(inputCode)
                .phaseSetting(inputCode)
                .build();
        intComputer.start();
        intComputer.setRunning();
        intComputer.waitForIntcomputer();
        List<Integer> intComputerOutput = intComputer.getIntComputerOutput();
        return intComputerOutput.get(intComputerOutput.size() - 1);
    }

}
