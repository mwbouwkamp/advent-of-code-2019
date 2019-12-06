package day6;

import java.util.*;

public class SolverDay6 {

    public int getTotalOrbits(Map<String, SpaceObject> spaceObjectMap) {
        return spaceObjectMap.values().stream()
                .mapToInt(so -> so.getOrbits(spaceObjectMap))
                .sum();
    }

    public int getDistanceToSanta(Map<String, SpaceObject> spaceObjectMap) {
        String yourPath = spaceObjectMap.get("YOU").getOrbitSteps(spaceObjectMap);
        String santaPath = spaceObjectMap.get("SAN").getOrbitSteps(spaceObjectMap);
        List<String> yourPathSpaceObjects = new ArrayList<>(Arrays.asList(yourPath.split("-")));
        List<String> santaPathSpaceObjects = new ArrayList<>(Arrays.asList(santaPath.split("-")));
        while (true) {
            String yourObject = yourPathSpaceObjects.remove(0);
            String santaObject = santaPathSpaceObjects.remove(0);
            if (!yourObject.equals(santaObject)) {
                return yourPathSpaceObjects.size() + santaPathSpaceObjects.size();
            }
        }
    }
}
