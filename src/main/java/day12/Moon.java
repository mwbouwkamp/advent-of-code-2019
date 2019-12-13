package day12;

import java.util.Arrays;
import java.util.List;

public class Moon {
    private int[] position;
    private int[] velocity;

    public Moon(int xPos, int yPos, int zPos) {
        this.position = new int[] {xPos, yPos, zPos};
        this.velocity = new int[3];
    }

    public void updateVelocity(List<Moon> moonList) {
        for (int i = 0; i < 3; i++) {
            velocity[i] += calcDeltaVelocity(moonList, i);
        }
    }

    public void updatePosition() {
        for (int i = 0; i < 3; i++) {
            position[i] += velocity[i];
        }
    }

    public int calcDeltaVelocity(List<Moon> moonList, int axis) {
        return moonList.stream()
                .filter(m -> m != this)
                .mapToInt(m -> getDeltaGravity(m, axis))
                .sum();
    }

    private int getDeltaGravity(Moon moon, int axis) {
        return Integer.compare(moon.getPosition()[axis], position[axis]);
    }

    public int[] getPosition() {
        return position;
    }

    public int[] getVelocity() {
        return velocity;
    }

    public int calculatePotential() {
        return Arrays.stream(position)
                .map(Math::abs)
                .sum();
    }

    public int calculateKinetic() {
        return Arrays.stream(velocity)
                .map(Math::abs)
                .sum();
    }
}
