package day10;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Asteroid {
    Point location;

    public Asteroid(Point location) {
        this.location = location;
    }

    public int countVisible(List<Asteroid> asteroids) {
        return asteroids.stream()
                .filter(a -> a != this)
                .map(a -> a.getAngle(this))
                .collect(Collectors.toSet())
                .size();
    }

    public Point getLocation() {
        return location;
    }

    public double getAngle(Asteroid origin) {
        int deltaX = location.x - origin.getLocation().x;
        int deltaY = location.y - origin.getLocation().y;
        double angle = 360 - Math.atan((1.0 * deltaX) / (deltaY))* 180 / Math.PI + 180;
        if (deltaY > 0) {
            return (angle) % 360;
        } else if (deltaY < 0) {
            return (angle + 180) % 360;
        } else {
            return (angle + 360) % 360;
        }
    }

    public double getDistance(Asteroid origin) {
        int deltaX = location.x - origin.getLocation().x;
        int deltaY = location.y - origin.getLocation().y;
        return Math.sqrt(
                deltaX * deltaX + deltaY * deltaY
        );
    }

//    public static class LineOfSight {
//        String quadrant;
//        int distance;
//        double xyRatio;
//        Asteroid asteroid;
//
//        LineOfSight(String lineOfSightString, Asteroid asteroid) {
//            String[] lineOfSightArray = lineOfSightString.split(",");
//            this.quadrant = lineOfSightArray[0];
//            if (this.quadrant.contains("XAXIS") || this.quadrant.contains("YAXIS")) {
//                this.distance = Integer.parseInt(lineOfSightArray[1]);
//            } else {
//                this.distance = Integer.parseInt(lineOfSightArray[1]) * Integer.parseInt(lineOfSightArray[1]) +
//                        Integer.parseInt(lineOfSightArray[2]) *  Integer.parseInt(lineOfSightArray[2]);
//                this.xyRatio = 1.0 * Integer.parseInt(lineOfSightArray[1]) / Integer.parseInt(lineOfSightArray[2]);
//            }
//            this.asteroid = asteroid;
//        }
//    }
//
//    public String getLineOfSightString(Asteroid asteroid, int mode) {
//        StringBuilder lineOfSight = new StringBuilder();
//        int relativeLocationX = asteroid.getLocation().x - location.x;
//        int relativeLocationY = asteroid.getLocation().y - location.y;
//        if (relativeLocationX == 0) {
//            if (relativeLocationY > 0) {
//                lineOfSight.append("YAXSPLUS");
//            } else {
//                lineOfSight.append("YAXSMIN");
//            }
//            if (mode == 2) {
//                lineOfSight
//                        .append(",")
//                        .append(Math.abs(relativeLocationY));
//            }
//        } else if (relativeLocationY == 0) {
//            if (relativeLocationX > 0) {
//                lineOfSight.append("XAXSPLUS");
//            } else {
//                lineOfSight.append("XXAXSMIN");
//            }
//            if (mode == 2) {
//                lineOfSight
//                        .append(",")
//                        .append(Math.abs(relativeLocationX));
//            }
//        } else {
//            double ratio = 1.0 * relativeLocationX / relativeLocationY;
//            if (relativeLocationX > 0) {
//                lineOfSight.append("XPLS");
//            } else {
//                lineOfSight.append("XMIN");
//            }
//            if (relativeLocationY > 0) {
//                lineOfSight.append("YPLS");
//            } else {
//                lineOfSight.append("YMIN");
//            }
//            if (mode == 1) {
//                lineOfSight.append(ratio);
//            } else {
//                lineOfSight
//                        .append(",")
//                        .append(Math.abs(relativeLocationX))
//                        .append(",")
//                        .append(Math.abs(relativeLocationY));
//            }
//        }
//        return lineOfSight.toString();
//    }
}
