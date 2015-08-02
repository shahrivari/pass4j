package amu.saeed.pass4j.simple;

import java.util.List;
import java.util.Random;

/**
 * Created by Saeed on 7/25/2015.
 */
public class Test {
    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 2, 3, 4, 5};
        BubbleSortState goal = new BubbleSortState(array);
        BubbleSortState root = new BubbleSortState(shuffleArray(array));
        List<State> dfs = new DepthFirstSearch().solve(root, goal);
        System.out.println("DFS->");
        for (State s : dfs)
            System.out.println(s);
        List<State> bfs = new BreadthFirstSearch().solve(root, goal);
        System.out.println("BFS->");
        for (State s : bfs)
            System.out.println(s);
    }

    // Implementing Fisher–Yates shuffle
    static int[] shuffleArray(int[] input) {
        int[] ints = input.clone();
        Random rnd = new Random();
        for (int i = ints.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ints[index];
            ints[index] = ints[i];
            ints[i] = a;
        }
        return ints;
    }
}
