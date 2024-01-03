package cz.todr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public final class Main {

    private static Collection<Space> addToSpace(Space s, int count) {
        var solutions = new ArrayList<Space>();
        var queue = new ArrayList<Space>();
        queue.add(s);

        long last = System.nanoTime();
        int lowestLevelSeen = Integer.MAX_VALUE;
        long evaluated = 0;

        do {
            var current = queue.removeLast();

            evaluated++;
            lowestLevelSeen = Math.min(lowestLevelSeen, current.count());
            long now = System.nanoTime();
            if (now - last > 1_000_000_000L) {
                System.out.println(queue.size() + " queued, level: " + current.count() + "   " + (evaluated / ((now - last) / 1_000_000)) + " ops/ms, solutions: " + solutions.size());
                evaluated = 0;
                lowestLevelSeen = current.count();
                last = now;
            }

            var solution = testCurrent(current, queue, count);
            if (solution != null) {
                System.out.println(solution);
                solutions.add(solution);
            }

        } while (!queue.isEmpty());

        return solutions;
    }

    private static Space testCurrent(Space current, ArrayList<Space> queue, int count) {
        for (int[] points : Brick.allFromLookup(current.firstFreeIndex())) {
            if (allFree(current, points)) {
                Space newSpace = current.addBrick(points);
                if (newSpace.count() == count) {
                    return newSpace;
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

    static void findSolution() {
        var start = System.nanoTime();
        var solutions = addToSpace(new Space(), 25);
        System.out.println("time = " + (System.nanoTime() - start) / 1000_000);
        System.out.println(solutions);
    }

    public static void main(String[] args) {
        findSolution();
    }
}
