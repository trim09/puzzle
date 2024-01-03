package cz.todr;

import java.util.Arrays;

import static cz.todr.Point.DIMENSION;

record Space(int[] space, int count, int maybeFree) {
    public Space() {
        this(new int[DIMENSION * DIMENSION * DIMENSION], 0,0);
    }

    public boolean isFree(int index) {
        return index >= 0 && index < space.length && space[index] == 0;
    }

    public int getValue(Point p) {
        return space[p.index()];
    }

    public Space addBrick(int[] indices) {
        int newCount = count + 1;

        int[] clone = Arrays.copyOf(space, space.length);
        for (int i = 0; i < indices.length; i++) {
            clone[indices[i]] = newCount;
        }

        return new Space(clone, newCount, indices[0] + 1);
    }

    public int firstFreeIndex() {
        for (int i = maybeFree; i < space.length; i++) {
            if (space[i] == 0)
                return i;
        }

        throw new IllegalStateException();
    }


    @Override
    public String toString() {
        return "Space{\n" + ArrayUtils.arrToString(this) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space1 = (Space) o;
        return Arrays.equals(space, space1.space);
    }

    public int hashCode() {
        return Arrays.hashCode(space);
    }
}
