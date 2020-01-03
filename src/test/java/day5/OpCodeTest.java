package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.OpCode;

import static org.junit.jupiter.api.Assertions.*;

class OpCodeTest {

    @ParameterizedTest
    @CsvSource({
            "1,1,0,0,0,0",
            "11,11,0,0,0,0",
            "101,1,1,0,0,0",
            "111,11,1,0,0,0",
            "1101,1,1,1,0",
            "11101,1,1,1,1"
    })
    void test_should_create_correct_opCode(int code, int expectedInstruction, int expectedModeInput1, int expectedModeInput2, int expectedModeResult) {
        OpCode opCode = new OpCode(code);
        assertEquals(expectedInstruction, opCode.getInstruction());
        assertEquals(expectedModeInput1, opCode.getMode1());
        assertEquals(expectedModeInput2, opCode.getMode2());
        assertEquals(expectedModeResult, opCode.getMode3());
    }

}