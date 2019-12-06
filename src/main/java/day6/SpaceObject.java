package day6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpaceObject {
    String name;
    String parent;
    List<String> children;

    public SpaceObject(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public int getOrbits(Map<String, SpaceObject> spaceObjectMap) {
        return name.equals("COM") ? 0 : spaceObjectMap.get(parent).getOrbits(spaceObjectMap) + 1;
    }

    public String getOrbitSteps(Map<String, SpaceObject> spaceObjectMap) {
        return name.equals("COM") ? "COM" : spaceObjectMap.get(parent).getOrbitSteps(spaceObjectMap) + "-" + name;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public void addChild(String child) {
        children.add(child);
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
