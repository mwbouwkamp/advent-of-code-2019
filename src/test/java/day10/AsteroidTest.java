package day10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class AsteroidTest {

    @ParameterizedTest
    @CsvSource({
            "0,1,0",
            "1,1,45",
            "1,0,90",
            "1,-1,135",
            "0,-1,180",
            "-1,-1,225",
            "-1,0,270",
    })
    void getAngle(int x, int y, int angle) {
        Asteroid origin = new Asteroid(new Point(0,0));
        assertEquals(angle, Math.round(new Asteroid(new Point(x, y)).getAngle(origin)));
    }
}