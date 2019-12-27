package day20part2;

import java.awt.*;

public class Portal {
    String portalName;
    Point toOuter, toInner;

    public Portal(String portalName) {
        this.portalName = portalName;
    }

    public Point getToOuter() {
        return toOuter;
    }

    public Point getToInner() {
        return toInner;
    }

    public void setToOuter(Point toOuter) {
        this.toOuter = toOuter;
    }

    public void setToInner(Point toInner) {
        this.toInner = toInner;
    }
}
