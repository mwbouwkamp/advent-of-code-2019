package day1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolverDay1Test {

    public static final List<Long> EMPTY_LIST = new ArrayList<>();

    @Test
    void calculate_TotalFuel_should_return_default_value_for_empty_list() {
        assertEquals(-1, new SolverDay1().calculateTotalFuel(EMPTY_LIST));
    }

    @Test
    void calculateTotalFuelIncludingFuelForTransport_should_return_default_value_for_empty_list() {
        assertEquals(-1, new SolverDay1().calculateTotalFuelIncludingFuelForFuelTransport(EMPTY_LIST));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "3, 0",
            "6, 0",
            "8, 0",
            "9, 1",
            "11, 1",
            "12, 2"
    })
    void calculateFuel_should_return_fuel(long input, long expected) {
        assertEquals(expected, new SolverDay1().calculateFuel(input));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "9, 10", //9 -> 1                       --> 1 + 9 = 10
            "32, 40", //32 -> 8 -> 0                --> 32 + 8 = 40
            "33, 43", //33 -> 9 -> 1                --> 33 + 9 + 1 = 43
            "34, 44", //33 -> 9 -> 1                --> 34 + 9 + 1 = 44
            "105, 148" //105 -> 33 -> 9 -> 1        --> 105 + 33 + 9 + 1 = 148
    })
    void getAdditionalFuelRequired(long input, long expected) {
        assertEquals(expected, new SolverDay1().getAdditionalFuelRequired(input));
    }

}