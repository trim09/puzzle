package cz.todr;

import static cz.todr.Point.DIMENSION;

public class ArrayUtils {

    public static String arrToString(Space space) {
        String result = "";
        for (int z = 0; z < DIMENSION; z++) {
            for (int y = 0; y < DIMENSION; y++) {
                for (int x = 0; x < DIMENSION; x++) {
                    result += String.format("%3d", space.getValue(new Point(x, y, z)));
                }
                result += "      ";
            }
            result += "\n";
        }
        return result;
    }


}
