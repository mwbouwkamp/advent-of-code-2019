package day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SolverDay4Test {

    @ParameterizedTest
    @CsvSource({
            "1,1",
            "22,22",
            "122,122",
            "333,''",
            "122333, 122",
            "122333444455556666, 122"
    })
    void removeTrippleAndHigher(String input, String expected) {
        assertEquals(expected, new SolverDay4().removeTrippleAndHigher(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'1',true",
            "'1,2',true",
            "'2,1',false",
            "'1,2,3,4,5',true",
            "'5,4,3,2,1',false",
            "'1,2,3,2,3',false",
            "'1,1',true"
    })
    void integerListAscending(String input, boolean expected) {
        List<Integer> intList = getIntegerListFromCSVString(input);
        boolean result = new SolverDay4().integerListAscending(intList);
        assertEquals(expected, result);
    }

    private List<Integer> getIntegerListFromCSVString(String input) {
        return Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
    }

    @ParameterizedTest
    @CsvSource({
            "'1',false",
            "'1,1',true",
            "'1,1,1',true"
    })
    void doubleIntegers(String input, boolean expected) {
        List<Integer> intList = getIntegerListFromCSVString(input);
        boolean result = new SolverDay4().doubleIntegers(intList);
        assertEquals(expected, result);
    }
}