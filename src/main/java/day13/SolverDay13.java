package day13;

import utils.IntComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class SolverDay13 {

    public long playGame(List<Long> numbers) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(numbers.get(1))
                .phaseSetting(numbers.get(1))
                .isHaltable()
                .build();
        intComputer.start();
        while (!intComputer.hasTerminated()) {

        }

        return 0L;
    }


    public List<Block> getBlocksFromInput(List<Long> numbers) {
        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .phaseSetting(0)
                .build();
        intComputer.setRunning();
        intComputer.start();
        while (!intComputer.hasTerminated()) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        List<Long> result =  intComputer.getIntComputerOutput();
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < result.size(); i = i + 3) {
            blocks.add(new Block(result.get(i), result.get(i + 1), result.get(i + 2)));
        }
        return blocks;
    }
}
