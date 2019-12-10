package day10;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Asteroid {
    Point location;

    public Asteroid(Point location) {
        this.location = location;
    }

    public int countVisible(List<Asteroid> asteroids) {
        Set<String> lineOfSightsPresent = new HashSet<>();
        for (Asteroid asteroid: asteroids) {
            StringBuilder lineOfSight = new StringBuilder();
            int relativeLocationX = asteroid.getLocation().x - location.x;
            int relativeLocationY = asteroid.getLocation().y - location.y;
            if (relativeLocationX == 0) {
                if (relativeLocationY > 0) {
                    lineOfSight.append("YAXISPLUS");
                } else {
                    lineOfSight.append("YAXISMIN");
                }
            } else if (relativeLocationY == 0) {
                if (relativeLocationX > 0) {
                    lineOfSight.append("XAXISPLUS");
                } else {
                    lineOfSight.append("XXAXISMIN");
                }
            } else {
                double ratio = 1.0 * relativeLocationX / relativeLocationY;
                if (relativeLocationX > 0) {
                    lineOfSight.append("XPLUS");
                } else {
                    lineOfSight.append("XMIN");
                }
                if (relativeLocationY > 0) {
                    lineOfSight.append("YPLUS");
                } else {
                    lineOfSight.append("YMIN");
                }
                lineOfSight.append(ratio);
            }
            lineOfSightsPresent.add(lineOfSight.toString());
        }
        return lineOfSightsPresent.size();
    }

    public Point getLocation() {
        return location;
    }
}
