package day8;

import java.util.ArrayList;
import java.util.List;

public class Image {
    List<List<List<Integer>>> layers;
    int width;
    int height;

    public Image(int width, int height, List<Integer> numbers) {
        this.width = width;
        this.height = height;
        this.layers = new ArrayList<>();
        int numLayers = numbers.size() / width / height;
        for (int i = 0; i < numLayers; i++) {
            List<List<Integer>> rows = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                List<Integer> row = numbers.subList(i * (width * height) + j * width, i * (width * height) + (j + 1) * width);
                rows.add(row);
            }
            this.layers.add(rows);
        }
    }

    public Image() {}

    public Image(List<List<List<Integer>>> layers) {
        this.layers = layers;
    }

    public long onesTimesTwosLayerWithFewestZeros() {
        return onesTimesTwos(getLayerWithFewestZeros());
    }

    public List<List<Integer>> getLayerWithFewestZeros() {
        return layers.stream()
                .reduce((a, b) -> countNumbers(a, 0) < countNumbers(b, 0) ? a : b)
                .orElse(null);
    }

    public long onesTimesTwos(List<List<Integer>> layer) {
        return countNumbers(layer, 1) * countNumbers(layer, 2);
    }

    public long countNumbers(List<List<Integer>> layer, int number) {
        return layer.stream()
                .flatMap(List::stream)
                .filter(i -> i == number)
                .count();
    }

    private int[][] process() {
        int[][] processedImage = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = 2;
                for (List<List<Integer>> layer: layers) {
                    int newPixel = layer.get(i).get(j);
                    if (newPixel == 0 || newPixel == 1) {
                        pixel = newPixel;
                        break;
                    }
                }
                processedImage[i][j] = pixel;
            }
        }
        return processedImage;
    }

    @Override
    public String toString() {
        int[][] picture = process();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < width; j++) {
                stringBuilder.append(picture[i][j] == 0 ? " " : "*");
            }
        }
        return stringBuilder.toString();
    }
}
