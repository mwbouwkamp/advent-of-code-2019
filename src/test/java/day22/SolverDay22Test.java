package day22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.w3c.dom.Text;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class SolverDay22Test {

    @Test
    void testExample1() {
        long[] inputArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] expectedArr = {0, 3, 6, 9, 2, 5, 8, 1, 4, 7};

        Map<Long, Long> input = getMap(inputArr);
        Map<Long, Long> expected = getMap(expectedArr);

        List<String> instructions = new ArrayList<>();
        instructions.add("deal with increment 7");
        instructions.add("deal into new stack");
        instructions.add("deal into new stack");

        SolverDay22 solver = new SolverDay22(input.size());
        solver.process(instructions);

        performTests(expected, solver.getCards());
    }

    @Test
    void testExample2() {
        long[] inputArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        long[] expectedArr = {9, 2, 5, 8, 1, 4, 7, 0, 3, 6};

        Map<Long, Long> input = getMap(inputArr);
        Map<Long, Long> expected = getMap(expectedArr);

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

        SolverDay22 solver = new SolverDay22(input.size());
        solver.process(instructions);

        performTests(expected, solver.getCards());
    }

    @Test
    void dealWithIncrement() {
        long[] inputArr = {0, 1, 2, 3, 4};
        long[] expected2Arr = {0, 3, 1, 4, 2};
        long[] expected3Arr = {0, 2, 4, 1, 3};

        Map<Long, Long> input = getMap(inputArr);
        Map<Long, Long> expected2 = getMap(expected2Arr);
        Map<Long, Long> expected3 = getMap(expected3Arr);

        Map<Long, Long> result2 = new SolverDay22(input.size()).dealWithIncrement(input, 2);
        Map<Long, Long> result3 = new SolverDay22(input.size()).dealWithIncrement(input, 3);

        performTests(expected2, result2);
        performTests(expected3, result3);
    }

    @Test
    void cut() {
        long[] inputArr = {0, 1, 2, 3};
        long[] output1Arr = {1, 2, 3, 0};
        long[] output2Arr = {2, 3, 0, 1};
        long[] outputMinus1Arr = {3, 0, 1, 2};
        long[] outputMinus3Arr = {1, 2, 3, 0};

        Map<Long, Long> input = getMap(inputArr);
        Map<Long, Long> expected1 = getMap(output1Arr);
        Map<Long, Long> expected2 = getMap(output2Arr);
        Map<Long, Long> expectedMinus1 = getMap(outputMinus1Arr);
        Map<Long, Long> expectedMinus3 = getMap(outputMinus3Arr);

        Map<Long, Long> result1 = new SolverDay22(input.size()).cut(input, 1);
        Map<Long, Long> result2 = new SolverDay22(input.size()).cut(input, 2);
        Map<Long, Long> resultMinus1 = new SolverDay22(input.size()).cut(input, -1);
        Map<Long, Long> resultMinus3 = new SolverDay22(input.size()).cut(input, -3);

        performTests(expected1, result1);
        performTests(expected2, result2);
        performTests(expectedMinus1, resultMinus1);
        performTests(expectedMinus3, resultMinus3);
    }

    @Test
    void dealIntoNewStack() {
        long[] inputArr = {0, 1, 2, 3};
        long[] expectedArr = {3, 2, 1, 0};

        Map<Long, Long> input = getMap(inputArr);
        Map<Long, Long> expected = getMap(expectedArr);
        Map<Long, Long> result = new SolverDay22(input.size()).dealIntoNewStack(input);

        performTests(expected, result);
    }

    private void performTests(Map<Long, Long> expected, Map<Long, Long> result) {
        for (long i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), result.get(i));
        }
    }

    private Map<Long, Long> getMap(long[] values) {
        return LongStream.range(0, values.length)
                .boxed()
                .collect(Collectors.toMap(k -> k, k -> values[Math.toIntExact(k)]));
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