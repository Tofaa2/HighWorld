package quest.highworld.util;

import lombok.Getter;

/**
 * @param x1 We know the y is always 0-256, so we don't need to store it
 */
public record Cuboid(@Getter int x1, @Getter int z1, @Getter int x2, @Getter int z2) {


    public boolean isInside(int x, int z) {
        return x >= x1 && x <= x2 && z >= z1 && z <= z2;
    }
}
