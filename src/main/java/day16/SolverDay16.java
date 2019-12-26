package day16;

import utils.InputUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolverDay16 {

    List<Integer> signal;
    int cycles;
    int offset;
    List<Integer> basePattern;
    List<List<Integer>> patterns;

    public SolverDay16(String input, int cycles, int offset) {
        this.signal = InputUtils.getSingleDigitsFromString(input);
        this.cycles = cycles;
        this.offset = offset;
        this.basePattern = getBasePattern();
//        this.patterns = getPatterns(input.length(), basePattern);
    }

    public String solve() {
        signal = solve(signal, patterns, cycles);
        return getAnswer(signal, offset);

    }

    private String getAnswer(List<Integer> signal, int offSet) {
        StringBuilder answer = new StringBuilder();
        for (int i = offSet; i < 8 + offSet; i++) {
            answer.append(signal.get(i));
        }
        return answer.toString();
    }

    private List<Integer> solve(List<Integer> signal, List<List<Integer>> patterns, int cycles) {
        for (int i = 0; i < cycles; i++) {
            System.out.println(i);
            signal = performCycle(signal);
        }
        return signal;
    }

    private List<Integer> performCycle(List<Integer> signal) {
        int signalSize = signal.size();
        List<Integer> newSignal = new ArrayList<>();
        for (int i = 0; i < signalSize; i++) {
//            System.out.print(i + " ");
            int result = calcResult(signal, i);
            newSignal.add(Math.abs(result) % 10);
        }
        signal = newSignal;
        return signal;
    }

    //15:18

    private int calcResult(List<Integer> signal, int start) {
        return (int) IntStream.range(start, signal.size())
                .parallel()
                .mapToLong(n -> (signal.get(n) * basePattern.get(((n + 1) / (start + 1)) % basePattern.size()) % 10))
                .sum() % 10;
    }

////    private List<List<Integer>> getPatterns(int signalSize, List<Integer> basePattern) {
////        return IntStream.range(1, signalSize + 1)
////                .parallel()
////                .boxed()
////                .map(n -> getPatternList(signalSize, basePattern, n))
////                .collect(Collectors.toList());
////    }
////
//    private List<Integer> getPatternList(int signalSize, List<Integer> basePattern, int n) {
//        List<Integer> pattern =  IntStream.range(0, signalSize + 1)
//                .parallel()
//                .boxed()
//                .mapToInt(i -> basePattern.get((i / n) % basePattern.size()))
//                .boxed()
//                .collect(Collectors.toList());
//        pattern.remove(0);
//        return pattern;
//    }
//
    private List<Integer> getBasePattern() {
        List<Integer> basePattern = new ArrayList<>();
        basePattern.add(0);
        basePattern.add(1);
        basePattern.add(0);
        basePattern.add(-1);
        return basePattern;
    }

}
