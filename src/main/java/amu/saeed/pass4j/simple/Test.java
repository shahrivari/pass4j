package amu.saeed.pass4j.simple;

import java.util.List;

/**
 * Created by Saeed on 7/25/2015.
 */
public class Test {
    public static void main(String[] args) {
        BubbleSortState goal = new BubbleSortState(new int[]{0, 1, 2, 3, 4, 5});
        BubbleSortState root = new BubbleSortState(new int[]{5, 4, 3, 2, 1, 0});
        List<State> dfs = new DepthFirstSearch().solve(root, goal);
        System.out.println("DFS->");
        for (State s : dfs)
            System.out.println(s);
        List<State> bfs = new BreadthFirstSearch().solve(root, goal);
        System.out.println("BFS->");
        for (State s : bfs)
            System.out.println(s);
    }
}
