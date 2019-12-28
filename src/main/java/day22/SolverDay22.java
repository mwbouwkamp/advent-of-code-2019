package day22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class SolverDay22 {
    long dimension;
    private Map<Long, Long> cards;

    public SolverDay22(long dimension) {
        this.dimension = dimension;
        this.cards = LongStream.range(0, dimension)
                .parallel()
                .boxed()
                .collect(Collectors.toMap(k -> k, k -> k));
    }

    public long solve(List<String> instructions, long card, long cycles) {
        process(instructions);
        for (long i = 0; i < cycles; i++) {
            card = cards.get(card);
//            System.out.println(cards.get(card));
        }
        return cards.get(card);
    }

    public void process(List<String> instructions) {
        for (String instruction: instructions) {
            String instructionText = instruction
                    .replaceAll("[0-9]", "")
                    .replaceAll("-", "")
                    .replaceAll(" ", "");
            switch (instructionText) {
                case "dealintonewstack":
                    cards = dealIntoNewStack(cards);
                    break;
                case "cut":
                    cards = cut(cards, getNumberFromInstruction(instruction));
                    break;
                case "dealwithincrement":
                    cards = dealWithIncrement(cards, getNumberFromInstruction(instruction));
                    break;
                default:
                    throw new IllegalArgumentException("instruction not supported: " + instruction);
            }
        }
    }

    public Map<Long, Long> dealWithIncrement(Map<Long, Long> cards, int increment) {
        Map<Long, Long> newCards = new HashMap<>();
        for (long i = 0; i < dimension; i++) {
            newCards.put(i * increment % dimension, cards.get(i));
        }
        return newCards;
    }

    public Map<Long, Long> cut(Map<Long, Long> cards, int position) {
        return cards.keySet().stream()
                .collect(Collectors.toMap(k -> k, k -> cards.get((dimension + k + position) % dimension)));
    }

    public Map<Long, Long> dealIntoNewStack(Map<Long, Long> cards) {
        return cards.keySet().stream()
                .collect(Collectors.toMap(k -> k, k -> cards.get(dimension - 1 - k)));
    }

    public int getNumberFromInstruction(String instruction) {
        String[] instructionParts = instruction.split(" ");
        return Integer.parseInt(instructionParts[instructionParts.length - 1]);
    }

    public Map<Long, Long> getCards() {
        return cards;
    }

    public long getPosition(long card) {
        for (long key: cards.keySet()) {
            if (cards.get(key) == card) {
                return key;
            }
        }
        return -1;
    }
}
