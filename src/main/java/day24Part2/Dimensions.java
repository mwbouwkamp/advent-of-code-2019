package day24Part2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Dimensions {
    Map<Integer, Grid> dimensionsMap;

    public Dimensions(List<String> input) {
        this.dimensionsMap = new HashMap<>();
        Grid startingGrid = new Grid(input, 0);
        dimensionsMap.put(0, startingGrid);
        growDimensions();
        startingGrid.makeInternalConnections();
        startingGrid.makeExternalConnections(this);
    }

    public Dimensions(Map<Integer, Grid> dimensionsMap) {
        this.dimensionsMap = dimensionsMap;
        growDimensions();
        int maxDimension = dimensionsMap.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);
        int minDimension = dimensionsMap.keySet().stream()
                .min(Integer::compareTo)
                .orElse(0);
        for (int dimension: dimensionsMap.keySet()) {
            dimensionsMap.get(dimension).makeInternalConnections();
            if (dimension != minDimension) {
                dimensionsMap.get(dimension).makeExternalConnections("OUTER", this);
            }
            if (dimension != maxDimension) {
                dimensionsMap.get(dimension).makeExternalConnections("INNER", this);
            }
        }
    }

    public Dimensions process() {
        growDimensions();
        Map<Integer, Grid> newDimensionsMap = new HashMap<>();
        for (int dimension: dimensionsMap.keySet()) {
            newDimensionsMap.put(dimension, dimensionsMap.get(dimension).process());
        }
        return new Dimensions(newDimensionsMap);
    }

    public Map<Integer, Grid> getDimensionsMap() {
        return dimensionsMap;
    }

    public long countBugs() {
        return dimensionsMap.values().stream()
                .mapToLong(Grid::CountBugs)
                .sum();
    }

    public void growDimensions() {
        growDimensions("OUTER");
        growDimensions("INNER");
    }

    public void growDimensions(String mode) {
        int dimension = mode.equals("OUTER")
                ? dimensionsMap.keySet().stream()
                        .min(Integer::compareTo)
                        .orElse(0)
                : dimensionsMap.keySet().stream()
                        .max(Integer::compareTo)
                        .orElse(0);
        int newDimension = mode.equals("OUTER")
                ? dimension - 1
                : dimension + 1;
        if (dimensionsMap.get(dimension).borderContainsBugs(mode)) {
            String line = IntStream.range(0, dimensionsMap.get(0).getWidth())
                    .boxed()
                    .map(n -> ".")
                    .reduce((a, b) -> a + b)
                    .orElse(null);
            List<String> input = IntStream.range(0, dimensionsMap.get(0).getHeight())
                    .boxed()
                    .map(n -> line)
                    .collect(Collectors.toList());
            dimensionsMap.put(newDimension, new Grid(input, newDimension));
        }
    }


}
