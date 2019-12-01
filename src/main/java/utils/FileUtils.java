package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private static final String PATH = "E:\\adventOfCode2019\\";

    public static List<Long> getNumbersFromInput(String filename) throws FileNotFoundException {
        File file = new File(PATH + filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines()
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }
}
