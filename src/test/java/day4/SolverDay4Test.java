package day4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}