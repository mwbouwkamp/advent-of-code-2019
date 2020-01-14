package day21;

import java.util.*;

public class SpringScript {
    private Map<Character, Boolean> registers;
    private List<String> instructions;

    public SpringScript(String instructions) {
        this.instructions = Arrays.asList(instructions.split("\n"));
        this.registers = new HashMap<>();
        this.registers.put('T', false);
        this.registers.put('J', false);
        this.registers.put('A', false);
        this.registers.put('B', false);
        this.registers.put('C', false);
        this.registers.put('D', false);
    }

    public boolean performScript(String environment) {
        for (String register: "ABCD".split(""))
        if (environment.contains(register)) {
            registers.put(register.charAt(0), true);
        }
        int i = 0;
        while (true) {
            String instruction = instructions.get(i);
            if (instruction.equals("WALK")) {
                break;
            }
            performInstuction(instruction);
            i++;
        }
        return registers.get('J');
    }

    private void performInstuction(String instruction) {
        String[] instructionElements = instruction.split(" ");
        switch (instructionElements[0]) {
            case "AND":
                andXY(instructionElements[1].charAt(0), instructionElements[2].charAt(0));
                break;
            case "OR":
                orXY(instructionElements[1].charAt(0), instructionElements[2].charAt(0));
                break;
            case "NOT":
                notXY(instructionElements[1].charAt(0), instructionElements[2].charAt(0));
                break;
            default:
                throw new IllegalArgumentException("Instruction not allowed: " + instructionElements[0]);
        }
    }

    public void andXY(char x, char y) {
        registers.put(y, registers.get(x) && registers.get(y));
    }

    public void orXY(char x, char y) {
        registers.put(y, registers.get(x) || registers.get(y));
    }

    public void notXY(char x, char y) {
        registers.put(y, !registers.get(x));
    }

}
