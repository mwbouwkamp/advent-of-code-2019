package day22;

import java.util.List;
import java.util.stream.IntStream;

public class SolverDay22 {
    private int[] cards;

    public SolverDay22(int dimension) {
        this.cards = IntStream.range(0, dimension)
                .toArray();
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

    public int[] dealWithIncrement(int[] cards, int increment) {
        int[] newCards = new int[cards.length];
        for (int i = 0; i < newCards.length; i ++) {
            int ii = i * increment % newCards.length;
            newCards[i * increment % newCards.length] = cards[i];
        }
        return newCards;
    }

    public int[] cut(int[] cards, int position) {
        return IntStream.range(cards.length + position, 2 * cards.length + position)
                .map(n -> cards[n % cards.length])
                .toArray();
    }

    public int[] dealIntoNewStack(int[] cards) {
        return IntStream.range(0, cards.length)
                .map(n -> cards[cards.length - 1 - n])
                .toArray();
    }

    public int getNumberFromInstruction(String instruction) {
        String[] instructionParts = instruction.split(" ");
        return Integer.parseInt(instructionParts[instructionParts.length - 1]);
    }

    public int[] getCards() {
        return cards;
    }

    public int getPosition(int card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == card) {
                return i;
            }
        }
        return -1;
    }
}
