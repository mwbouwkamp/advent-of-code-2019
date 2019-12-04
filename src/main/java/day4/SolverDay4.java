package day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolverDay4 {

    public List<Integer> getValidPasswords(int start, int end) {
        List<Integer> validPasswords = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<Integer> digits = getDigitsFromInt(i);
            if (doubleIntegers(digits) && integerListAscending(digits)) {
                validPasswords.add(i);
            }
        }
        return validPasswords;
    }

    public List<Integer> getValidPasswordsPart2(int start, int end) {
        return getValidPasswords(start, end).stream()
                .filter(this::onlyDoubleIntegers)
                .collect(Collectors.toList());
    }

    public List<Integer> getDigitsFromInt(int number) {
        return Arrays.stream(Integer.toString(number).split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public boolean onlyDoubleIntegers(int number) {
        String numbersString = getDigitsFromInt(number).stream()
                .map(i -> Integer.toString(i))
                .reduce((a, b) -> a + b)
                .orElse("");
        numbersString = removeTrippleAndHigher(numbersString);
        List<Integer> reducedNumbers = Arrays.stream(numbersString.split(""))
                .filter(s -> s.length() > 0)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        return doubleIntegers(reducedNumbers);
    }

    public String removeTrippleAndHigher(String numbersString) {
        return numbersString
                .replaceAll("(\\d)\\1\\1\\1\\1\\1", "")
                .replaceAll("(\\d)\\1\\1\\1\\1", "")
                .replaceAll("(\\d)\\1\\1\\1", "")
                .replaceAll("(\\d)\\1\\1", "");
    }

    public boolean doubleIntegers(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i) == numbers.get(i - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean integerListAscending(List<Integer> numbers) {
        int curInt = numbers.get(0);
        for (int i = 1; i< numbers.size(); i++) {
            if (numbers.get(i) < numbers.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
