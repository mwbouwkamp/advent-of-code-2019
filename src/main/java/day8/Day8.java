package day8;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException {
        String input = FileUtils.getStringsFromInput("input8.txt").get(0);
        int with = 25;
        int height = 6;
        Image image = extractImage(input, with, height);
        System.out.print(image.onesTimesTwosLayerWithFewestZeros());
        System.out.println(image);


    }

    private static Image extractImage(String input, int width, int height) {
        List<Integer> numbers = Arrays.stream(input.split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        return new Image(width, height, numbers);
    }
}
