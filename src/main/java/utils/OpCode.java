package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OpCode {

    private long instruction;
    private int mode1;
    private int mode2;
    private int mode3;

    public OpCode(long opCode) {
        List<String> opCodeChars = Arrays.stream(Long.toString(opCode).split("")).collect(Collectors.toList());
        instruction = Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1));
        if (opCodeChars.size() > 0) {
            instruction += 10 * Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1));
        }
        mode1 = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
        mode2 = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
        mode3 = opCodeChars.size() > 0
                ? Integer.parseInt(opCodeChars.remove(opCodeChars.size() - 1))
                : 0;
    }

    public long getInstruction() {
        return instruction;
    }

    public int getMode1() {
        return mode1;
    }

    public int getMode2() {
        return mode2;
    }

    public int getMode3() {
        return mode3;
    }
}
