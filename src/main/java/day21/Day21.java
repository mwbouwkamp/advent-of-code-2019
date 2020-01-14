package day21;

import utils.FileUtils;
import utils.InputUtils;
import utils.IntComputer;

import java.io.FileNotFoundException;
import java.util.List;

import static java.lang.Thread.sleep;

public class Day21 {

    public static void main(String[] args) throws FileNotFoundException {
        String instructions = "Input instructions:\n\n" +
                "NOT A T\n" +
                "NOT B J\n" +
                "OR T J\n" +
                "NOT C T\n" +
                "OR T J\n" +
                "AND D J\n" +
                "WALK\n";
        String input = FileUtils.getStringsFromInput("input21.txt").get(0);
        List<Long> numbers = InputUtils.getLongsFromCSVInput(input);

        IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .isHaltable()
                .build();
        intComputer.start();
        for (char inputCode: instructions.toCharArray()) {
            System.out.print(inputCode + " -> ");
            intComputer.runCycle(inputCode);
            List<Long> resultList = intComputer.getIntComputerOutput();
            for (Long result: resultList) {
                System.out.print((char) Math.toIntExact(result) + "");
            }
            System.out.println();
        }
        System.out.println(intComputer.getIntComputerOutput().toString());
    }

}
