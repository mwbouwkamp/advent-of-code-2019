package day16;

import utils.FileUtils;
import utils.InputUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input16.txt").get(0);
        List<Integer> signal = InputUtils.getSingleDigitsFromString(input);
        List<List<Integer>> patterns = getPatterns(signal.size(), getBasePattern());

        signal = solve(signal, patterns, 100);
        System.out.println(getAnswer(signal, 0, 8));

        StringBuilder newInputBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            newInputBuilder.append(input);
        }
        input = newInputBuilder.toString();
        signal = InputUtils.getSingleDigitsFromString(input);
        patterns = getPatterns(signal.size(), getBasePattern());

        signal = solve(signal, patterns, 100);
        int offset = Integer.parseInt(input.substring(0, 8));
        System.out.println(getAnswer(signal, offset, 8));

    }

    private static String getAnswer(List<Integer> signal, int offSet, int length) {
        StringBuilder answer = new StringBuilder();
        for (int i = offSet; i < length + offSet; i++) {
            answer.append(signal.get(i));
        }
        return answer.toString();
    }

    private static List<Integer> solve(List<Integer> signal, List<List<Integer>> patterns, int cycles) {
        int signalSize = signal.size();
        for (int i = 0; i < cycles; i++) {
            List<Integer> newSignal = new ArrayList<>();
            for (int j = 0; j < signalSize; j++) {
                int result = 0;
                for (int k = 0; k < signalSize; k++) {
                    result += (signal.get(k) * patterns.get(j).get(k)) % 10;
                }
                newSignal.add(Math.abs(result) % 10);
            }
            signal = newSignal;
        }
        return signal;
    }

    private static List<List<Integer>> getPatterns(int signalSize, List<Integer> basePattern) {
        List<List<Integer>> patterns = new ArrayList<>();
        for (int i = 1; i <= signalSize; i++) {
            List<Integer> pattern = new ArrayList<>();
            while (pattern.size() < signalSize + 1) {
                for (Integer factor: basePattern) {
                    for (int j = 0; j < i; j++) {
                        pattern.add(factor);
                    }
                }
            }
            pattern.remove(0);
            patterns.add(pattern);
        }
        return patterns;
    }

    private static List<Integer> getBasePattern() {
        List<Integer> basePattern = new ArrayList<>();
        basePattern.add(0);
        basePattern.add(1);
        basePattern.add(0);
        basePattern.add(-1);
        return basePattern;
    }
}
