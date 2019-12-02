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
}
