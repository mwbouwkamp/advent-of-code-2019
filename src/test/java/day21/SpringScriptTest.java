package day21;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpringScriptTest {

    @ParameterizedTest
    @CsvSource({
            "A.CD, true"
    })

    public void test(String environment, boolean expected) {
        String instructions = "" +
                "NOT A T\n" +
                "NOT B T\n" +
                "OR T J\n" +
                "NOT C T\n" +
                "OR T J\n" +
                "AND D J\n" +
                "WALK";
        SpringScript springScript = new SpringScript(instructions);
        assertEquals(expected, springScript.performScript(environment));


    }


}