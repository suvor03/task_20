package ru.vsu.cs.suvorov_d_a;

import java.util.ArrayList;
import java.util.List;

public class CycleSearch {
    public static int[][] exampleOfGraph =
            {
                    {1, 2}, {1, 3}, {1, 4}, {2, 3},
                    {3, 4}, {2, 6}, {4, 6}, {7, 8},
                    {8, 9}, {9, 7}
            };

    public static List<int[]> cycles = new ArrayList<>();

    public static Object[] findNewCycles(int[] path) {
        int n = path[0];
        int x;
        int[] sub = new int[path.length + 1];

        for (int[] ints : exampleOfGraph)
            for (int y = 0; y <= 1; y++)
                if (ints[y] == n) {
                    x = ints[(y + 1) % 2];
                    if (!isVisited(x, path)) {
                        sub[0] = x;
                        System.arraycopy(path, 0, sub, 1, path.length);
                        findNewCycles(sub);
                    } else if ((path.length > 2) && (x == path[path.length - 1])) {
                        int[] p = normalize(path);
                        int[] inv = invert(p);
                        if (isNew(p) && isNew(inv)) {
                            cycles.add(p);
                        }
                    }
                }

        return new Object[0];
    }

    private static boolean equals(int[] a, int[] b) {
        boolean ret = (a[0] == b[0]) && (a.length == b.length);

        for (int i = 1; ret && (i < a.length); i++) {
            if (a[i] != b[i]) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    private static int[] invert(int[] path) {
        int[] p = new int[path.length];

        for (int i = 0; i < path.length; i++) {
            p[i] = path[path.length - 1 - i];
        }

        return normalize(p);
    }

    private static int[] normalize(int[] path) {
        int[] p = new int[path.length];
        int x = isSmallest(path);
        int n;

        System.arraycopy(path, 0, p, 0, path.length);

        while (p[0] != x) {
            n = p[0];
            System.arraycopy(p, 1, p, 0, p.length - 1);
            p[p.length - 1] = n;
        }

        return p;
    }

    private static boolean isNew(int[] path) {
        boolean ret = true;

        for(int[] p : cycles) {
            if (equals(p, path)) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    private static int isSmallest(int[] path) {
        int min = path[0];

        for (int p : path) {
            if (p < min) {
                min = p;
            }
        }

        return min;
    }

    private static boolean isVisited(int n, int[] path) {
        boolean ret = false;

        for (int p : path) {
            if (p == n) {
                ret = true;
                break;
            }
        }

        return ret;
    }
}