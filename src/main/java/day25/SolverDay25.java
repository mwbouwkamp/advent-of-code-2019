package day25;

import utils.IntComputer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class SolverDay25 {
    private IntComputer intComputer;

    public SolverDay25(List<Long> numbers) {
        this.intComputer = new IntComputer.IntComputerBuilder(numbers)
                .inputCode(0)
                .isHaltable()
                .build();
        this.intComputer.start();
    }

    public void solve() {
        while (true) {
            lookAround();
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            command += "\n";
            for (int i = 0; i < command.length(); i++) {
                System.out.println((char) intComputer.runCycle(command.charAt(i)));
            }
        }
    }

    private void lookAround() {
        String lastCharacters = "#########";
        while (!lastCharacters.equals("Command?\n")) {
            char character = (char) intComputer.runCycle(0);
            System.out.print(character);
            lastCharacters = lastCharacters.substring(1) + character;
        }
        System.out.print(">>> ");
    }

    private String getStringFromOutput(List<Long> output) {
        StringBuilder commandBuilder = new StringBuilder();
        for (long number : output) {
            commandBuilder.append((char) number);
        }
        return commandBuilder.toString();
    }

}
