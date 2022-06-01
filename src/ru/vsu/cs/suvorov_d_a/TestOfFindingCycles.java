package ru.vsu.cs.suvorov_d_a;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestOfFindingCycles {
    private final int[][] inputGraph;
    private final int[] expectedResult;

    public TestOfFindingCycles(int[][] inputGraph, int[] expectedResult) {
        this.inputGraph = inputGraph;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static List<Object[]> cases() {
        return Arrays.asList(new Object[][]{
                {new int[][]{{1, 2}, {1, 3}, {1, 4}, {2, 3},
                        {3, 4}, {2, 6}, {4, 6}, {7, 8},
                        {8, 9}, {9, 7}}, new int[]{7, 9, 8}},
        });
    }
    private static List<int[]> cycle = new ArrayList<>();

    private StringBuilder getActualResult(List<int[]> cycles, int[][] graph) {
        for (int i = 0; i < inputGraph.length; i++)
            for (int j = 0; j < inputGraph[i].length; j++)
            {
                CycleSearch.findNewCycles(new int[] {inputGraph[i][j]});
            }
        StringBuilder s = null;
        for (int[] cy : CycleSearch.cycles) {
            s = new StringBuilder("" + cy[0]);

            for (int i = 1; i < cy.length; i++) {
                s.append(", ").append(cy[i]);
            }

        }
        return s;
    }

    @Test
    public void findCyclesTest() {
        Assert.assertEquals(Arrays.toString(expectedResult), "[" + getActualResult(cycle, inputGraph) + "]");
    }
}