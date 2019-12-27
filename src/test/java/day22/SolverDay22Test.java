package day22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolverDay22Test {

    @Test
    void testExample1() {
        int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] output = {0, 3, 6, 9, 2, 5, 8, 1, 4, 7};
        List<String> instructions = new ArrayList<>();
        instructions.add("deal with increment 7");
        instructions.add("deal into new stack");
        instructions.add("deal into new stack");

        SolverDay22 solver = new SolverDay22(input.length);
        solver.process(instructions);

        assertEquals(Arrays.toString(output), Arrays.toString(solver.getCards()));
    }

    @Test
    void testExample2() {
        int[] input = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] output = {9, 2, 5, 8, 1, 4, 7, 0, 3, 6};
        List<String> instructions = new ArrayList<>();
        instructions.add("deal into new stack");
        instructions.add("cut -2");
        instructions.add("deal with increment 7");
        instructions.add("cut 8");
        instructions.add("cut -4");
        instructions.add("deal with increment 7");
        instructions.add("cut 3");
        instructions.add("deal with increment 9");
        instructions.add("deal with increment 3");
        instructions.add("cut -1");

        SolverDay22 solver = new SolverDay22(input.length);
        solver.process(instructions);

        assertEquals(Arrays.toString(output), Arrays.toString(solver.getCards()));
    }

    @Test
    void dealWithIncrement() {
        int[] input = {0, 1, 2, 3, 4};
        int[] output2 = {0, 3, 1, 4, 2};
        int[] output3 = {0, 2, 4, 1, 3};

        assertEquals(Arrays.toString(output2), Arrays.toString(new SolverDay22(5).dealWithIncrement(input, 2)));
        assertEquals(Arrays.toString(output3), Arrays.toString(new SolverDay22(5).dealWithIncrement(input, 3)));
    }

    @Test
    void cut() {
        int[] input = {0, 1, 2, 3};
        int[] output1 = {1, 2, 3, 0};
        int[] output2 = {2, 3, 0, 1};
        int[] outputMinus1 = {3, 0, 1, 2};
        int[] outputMinus3 = {1, 2, 3, 0};

        assertEquals(Arrays.toString(output1), Arrays.toString(new SolverDay22(4).cut(input, 1)));
        assertEquals(Arrays.toString(output2), Arrays.toString(new SolverDay22(4).cut(input, 2)));
        assertEquals(Arrays.toString(outputMinus1), Arrays.toString(new SolverDay22(4).cut(input, -1)));
        assertEquals(Arrays.toString(outputMinus3), Arrays.toString(new SolverDay22(4).cut(input, -3)));
    }

    @Test
    void dealIntoNewStack() {
        int[] input = {0, 1, 2, 3};
        int[] expected = {3, 2, 1, 0};
        assertEquals(Arrays.toString(expected), Arrays.toString(new SolverDay22(4).dealIntoNewStack(input)));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "a 1, 1",
            "a 1 2, 2",
            "-1, -1",
            "a -1, -1"
    })
    void getNumberFromInstruction_should_return_number(String instruction, int number) {
        assertEquals(number, new SolverDay22(0).getNumberFromInstruction(instruction));
    }
}