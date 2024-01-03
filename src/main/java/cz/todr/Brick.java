package cz.todr;

import java.util.ArrayList;
import java.util.List;

import static cz.todr.Point.DIMENSION;
import static cz.todr.Point.DIMENSION_POW_POW;

final class Brick {

    // [][][] = index -> List<Brick points>
    private static final int[][][] lookup = initLookup();

    public static int[][] allFromLookup(int index) {
        return lookup[index];
    }

    private static int[][][] initLookup() {
        int[][][] result = new int[DIMENSION_POW_POW][][];
        for (int i = 0; i < result.length; i++) {
            result[i] = all(Point.fromIndex(i)).toArray(int[][]::new);
        }
        return result;
    }

    private static List<int[]> all(Point p) {
        List<int[]> all = new ArrayList<>();

        addX(p, all);
        addY(p, all);
        if (p.z() + 1 < DIMENSION) {
            addBeaks(p, all);
            addZ(p, all);
        }
        return all;
    }

    private static int[] classic(Point p, Rotation rotation) {
        return new int[]{
                p.index(),
                rotation.withIncrement(p, 1, 0),
                rotation.withIncrement(p, 2, 0),
                rotation.withIncrement(p, 2, 1),
                rotation.withIncrement(p, 3, 0)
        };
    }

    private static int[] flipped(Point p, Rotation rotation) {
        return new int[]{
                p.index(),
                rotation.withIncrement(p, 1, 0),
                rotation.withIncrement(p, 1, 1),
                rotation.withIncrement(p, 2, 0),
                rotation.withIncrement(p, 3, 0)
        };
    }

    private static int[] beakClassic(Point p, Rotation rotation) {
        return new int[]{
                p.index(),
                rotation.withIncrement(p, 1, -2),
                rotation.withIncrement(p, 1, -1),
                rotation.withIncrement(p, 1, 0),
                rotation.withIncrement(p, 1, 1)
        };
    }

    private static void addX(Point p, List<int[]> all) {
        if (p.x() + 3 < DIMENSION) {
            if (p.y() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.XY));
                all.add(flipped(p, Rotation.XY));
            }
            if (p.z() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.XZ));
                all.add(flipped(p, Rotation.XZ));
            }
        }
    }

    private static void addY(Point p, List<int[]> all) {
        if (p.y() + 3 < DIMENSION) {
            if (p.x() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.YX));
                all.add(flipped(p, Rotation.YX));
            }
            if (p.x() - 1 >= 0) {
                all.add(classic(p, Rotation.YXm));
                all.add(flipped(p, Rotation.YXm));
            }
            if (p.z() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.YZ));
                all.add(flipped(p, Rotation.YZ));
            }
        }
    }

    private static void addZ(Point p, List<int[]> all) {
        if (p.z() + 3 < DIMENSION) {
            if (p.x() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.ZX));
                all.add(flipped(p, Rotation.ZX));
            }
            if (p.x() - 1 >= 0) {
                all.add(classic(p, Rotation.ZXm));
                all.add(flipped(p, Rotation.ZXm));
            }
            if (p.y() + 1 < DIMENSION) {
                all.add(classic(p, Rotation.ZY));
                all.add(flipped(p, Rotation.ZY));
            }
            if (p.y() - 1 >= 0) {
                all.add(classic(p, Rotation.ZYm));
                all.add(flipped(p, Rotation.ZYm));
            }
        }
    }

    private static void addBeaks(Point p, List<int[]> all) {
        if (p.x() - 2 >= 0 && p.x() + 1 < DIMENSION)
            all.add(beakClassic(p, Rotation.ZX));
        if (p.y() - 2 >= 0 && p.y() + 1 < DIMENSION)
            all.add(beakClassic(p, Rotation.ZY));
        if (p.x() - 1 >= 0 && p.x() + 2 < DIMENSION)
            all.add(beakClassic(p, Rotation.ZXm));
        if (p.y() - 1 >= 0 && p.y() + 2 < DIMENSION)
            all.add(beakClassic(p, Rotation.ZYm));
    }
}
