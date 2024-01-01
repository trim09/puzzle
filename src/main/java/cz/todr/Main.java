package cz.todr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static Collection<Space> solutions = new HashSet<>();

    public static Space addToSpace(Space s, int count) {
        var queue = new ArrayList<Space>();
        queue.add(s);

        long last = System.nanoTime();
        long evaluated = 0;

        do {
            var current = queue.removeLast();

            evaluated++;
            long now = System.nanoTime();
            if (now - last > 1_000_000_000L) {
                System.out.println(queue.size() + " queued, level: " + current.count() + "   " + (evaluated / ((now - last) / 1_000_000)) + " ops/ms, solutions: " + solutions.size());
                evaluated = 0;
                last = now;
            }

            Space space = testCurrent(current, queue, count);

            if (space != null) {
                return space;
            }

        } while (true);
    }

    private static Space testCurrent(Space current, ArrayList<Space> queue, int count) {
//        Point free = current.firstFreePoint();
//        List<int[]> all = Brick.all(free);
        int free = current.firstFreeIndex();
        int[][] all = Brick.allFromLookup(free);

        for (int[] points : all) {
            if (allFree(current, points)) {
                Space newSpace = current.addBrick(points);
                if (newSpace.count() == count) {
                    solutions.add(newSpace);
//                    return newSpace;
                    return null;
                } else {
                    queue.add(newSpace);
                }
            }
        }

        return null;
    }

    private static boolean allFree(Space current, int[] points) {
        for (int point : points) {
            if (!current.isFree(point))
                return false;
        }
        return true;
    }

    public static void findSolution() {
        var start = System.nanoTime();
        Space space = addToSpace(new Space(), 25);
        System.out.println("time = " + (System.nanoTime() - start) / 1000_000);
        System.out.println(space);
    }

    public static void main(String[] args) {
        findSolution();
    }
}
