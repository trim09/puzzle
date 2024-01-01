package cz.todr;

public record Point(int x, int y, int z) {

    public static final int DIMENSION = 5;
    public static final int DIMENSION_POW = DIMENSION * DIMENSION;
    public static final int DIMENSION_POW_POW = DIMENSION * DIMENSION * DIMENSION;

    public static Point fromIndex(int i) {
        if (i < 0 || i > DIMENSION_POW_POW)
            throw new IllegalStateException();

        int z1 = i / DIMENSION_POW;
        int y1 = (i / DIMENSION) - z1 * DIMENSION;
        int x1 = i % DIMENSION;

        return new Point(x1, y1, z1);
    }

    public int index() {
        return z * DIMENSION_POW + y * DIMENSION + x;
    }

    public static int indexAt(int x, int y, int z) {
//        if (x < 0 || y < 0 || z < 0 || x >= DIMENSION || y >= DIMENSION || z >= DIMENSION)
//            throw new IllegalStateException();
        return new Point(x, y, z).index();
    }
}
