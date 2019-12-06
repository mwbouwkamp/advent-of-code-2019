package day5;

import utils.InputUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OpCode {

    private int instruction;
    private int modeInput1;
    private int modeInput2;
    private int modeResult;

    public OpCode(int opCode) {
        List<String> opCodeChars = Arrays.stream(Integer.toString(opCode).split("")).collect(Collectors.toList());
        instruction = Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1));
        if (opCodeChars.size() > 0) {
            instruction += 10 * Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1));
        }
        modeInput1 = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
        modeInput2 = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
        modeResult = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
    }

    public int getInstruction() {
        return instruction;
    }

    public int getModeInput1() {
        return modeInput1;
    }

    public int getModeInput2() {
        return modeInput2;
    }

    public int getModeResult() {
        return modeResult;
    }
}
