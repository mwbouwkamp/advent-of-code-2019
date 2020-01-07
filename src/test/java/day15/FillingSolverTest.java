package day15;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FillingSolverTest {

    @Test
    void solve() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 2));
        points.add(new Point(0, 3));
        points.add(new Point(1, 0));
        points.add(new Point(-1, 0));

        FillingSolver fillingSolver = new FillingSolver();
        int result = fillingSolver.solve(new Point(0, 0), points);
        assertEquals(3, result);

        result = fillingSolver.solve(new Point(-1, 0), points);
        assertEquals(4, result);

    }
}