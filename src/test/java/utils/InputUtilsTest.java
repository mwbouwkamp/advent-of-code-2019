package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilsTest {

    @Test
    void getNumbersFromCSVInput_should_return_empty_list_for_empty_string() {
        String input = "";
        assertEquals(0, InputUtils.getNumbersFromCSVInput(input).size());
    }

    @Test
    void getNumbersFromCSVInput_should_return_list_from_singele_value() {
        String input = "2";
        List<Integer> result = InputUtils.getNumbersFromCSVInput(input);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0));
    }

    @Test
    void getNumbersFromCSVInput_should_return_list_from_csv() {
        String input = "2,4,8";
        List<Integer> result = InputUtils.getNumbersFromCSVInput(input);
        assertEquals(3, result.size());
        assertEquals(2, result.get(0));
        assertEquals(4, result.get(1));
        assertEquals(8, result.get(2));
    }

    @Test
    void getNumbersFromCSVInput_should_throw_NumberFormatException_for_nonNumeric_input() {
        String input = "a";
        assertThrows(NumberFormatException.class, () -> InputUtils.getNumbersFromCSVInput(input));
    }
}