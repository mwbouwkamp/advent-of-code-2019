package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private static final String PATH = "E:\\adventOfCode2019\\";

    public static List<String> getStringFromInput(String filename) throws FileNotFoundException {
        BufferedReader reader = getBufferedReaderFromFile(filename);
        return reader.lines()
                .collect(Collectors.toList());
    }

    public static List<Integer> getIntegersFromInput(String filename) throws FileNotFoundException {
        BufferedReader reader = getBufferedReaderFromFile(filename);
        return reader.lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<Long> getLongsFromInput(String filename) throws FileNotFoundException {
        BufferedReader reader = getBufferedReaderFromFile(filename);
        return reader.lines()
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    private static BufferedReader getBufferedReaderFromFile(String filename) throws FileNotFoundException {
        File file = new File(PATH + filename);
        return new BufferedReader(new FileReader(file));
    }
}
