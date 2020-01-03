package day13;

import java.awt.*;

public class Block {

    Long x, y;
    long type;

    public Block(long x, long y, long type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Long getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public long getType() {
        return type;
    }
}
