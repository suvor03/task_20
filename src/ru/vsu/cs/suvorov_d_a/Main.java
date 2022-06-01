package ru.vsu.cs.suvorov_d_a;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < CycleSearch.exampleOfGraph.length; i++)
            for (int j = 0; j < CycleSearch.exampleOfGraph[i].length; j++)
            {
                CycleSearch.findNewCycles(new int[] {CycleSearch.exampleOfGraph[i][j]});
            }

        System.out.println("Все циклы в неориентированном графе: ");

        for (int[] cy : CycleSearch.cycles)
        {
            StringBuilder s = new StringBuilder("" + cy[0]);

            for (int i = 1; i < cy.length; i++)
            {
                s.append(", ").append(cy[i]);
            }

            System.out.println(s);
        }
    }
}