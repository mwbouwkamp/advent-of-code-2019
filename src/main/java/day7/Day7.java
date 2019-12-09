package day7;

import utils.FileUtils;
import utils.InputUtils;
import utils.IntComputer;

import java.io.FileNotFoundException;
import java.util.List;

import static java.lang.Thread.sleep;

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input7.txt").get(0);
//        input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
        List<Integer> numbers = InputUtils.getNumbersFromCSVInput(input);

        System.out.println("Solution 7.1: " + new SolverDay7(numbers, 5, 0).getMaxOutput());

//        testCases();


//        System.out.println(new SolverDay7(numbers, 5, 5).getMaxOutputWithFeedback());





    }

    private static void testCases() {
        String input;
        List<Integer> numbers;
        input = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
//        input = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54," +
//                "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4," +
//                "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10";
        numbers = InputUtils.getNumbersFromCSVInput(input);

        int[] phaseSettings = new int[]{9,8,7,6,5};
//        int[] phaseSettings = new int[]{9,7,8,5,6};

        int result = 0;
        int i = 0;
        while (true) {
            for (int j = 0; j < 5; j++) {
                IntComputer intComputer = new IntComputer.IntComputerBuilder(numbers)
                        .inputCode(result)
                        .phaseSetting(phaseSettings[j])
                        .build();
                intComputer.start();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = intComputer.getIntComputerOutput().get(i);
                System.out.println("--- " + result + " ---" + (i * 5 + j));
            }
            i++;
        }
    }
}
