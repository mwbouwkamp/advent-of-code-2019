package day6;

import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> inputList = FileUtils.getStringsFromInput("input6.txt");
        Map<String, SpaceObject> spaceObjectMap = createSpaceObjectMap(inputList);
        setParentsChildren(inputList, spaceObjectMap);

        int totalOrbits = new SolverDay6().getTotalOrbits(spaceObjectMap);
        System.out.println("Solution Day 6.1: " + totalOrbits);

        int shortestPath = new SolverDay6().getDistanceToSanta(spaceObjectMap);
        System.out.println("Solution 6.2: "  + shortestPath);
    }

    private static void setParentsChildren(List<String> inputList, Map<String, SpaceObject> spaceObjectMap) {
        for (String input: inputList) {
            String[] spaceObjects = input.split("\\)");
            String parent = spaceObjects[0];
            String child  = spaceObjects[1];
            spaceObjectMap.get(parent).addChild(child);
            spaceObjectMap.get(child).setParent(parent);
        }
    }

    private static Map<String, SpaceObject> createSpaceObjectMap(List<String> inputList) {
        return inputList.stream()
                    .map(Day6::getObjectsFromInput)
                    .flatMap(List::stream)
                    .collect(Collectors.toMap(
                            SpaceObject::getName,
                            Function.identity(),
                            (a, b) -> a));
    }

    private static List<SpaceObject> getObjectsFromInput(String input) {
        return Arrays.stream(input.split("\\)"))
                .map(SpaceObject::new)
                .collect(Collectors.toList());
    }

}
