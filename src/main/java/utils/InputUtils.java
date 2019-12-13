package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtils {

    public static List<Integer> getNumbersFromCSVInput(String input) {
        return Arrays.stream(input.split(","))
                .filter(s -> s.length() > 0)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Long> getLongsFromCSVInput(String input) {
        return Arrays.stream(input.split(","))
                .filter(s -> s.length() > 0)
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<String> getStringsFromCSVInput(String input) {
        return Arrays.stream(input.split(","))
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());
    }
}
