package amu.saeed.pass4j;

import amu.saeed.pass4j.examples.TilePuzzle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

/**
 * Created by Saeed on 12/28/2014.
 */
public class BfsSolver {

    public static TilePuzzle solve(TilePuzzle init_state, final double max_f) {
        Queue<TilePuzzle> Q = new ArrayDeque<TilePuzzle>();
        HashSet<TilePuzzle> V = new HashSet<TilePuzzle>();
        V.add(init_state);
        Q.add(init_state);
        while (!Q.isEmpty()) {
            TilePuzzle top = Q.poll();
            if (top.distanceToGoal() == 0) {
                System.out.println("#Visited: " + V.size());
                System.out.println("#Moves: " + top.getActions().size());
                System.out.println(Arrays.toString(top.getActions().toArray()));
                return top;
            }
            if (top.fitness() > max_f)
                continue;
            for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
                TilePuzzle new_state = top.clone().move(act);
                if (new_state == null)
                    continue;
                if (!V.contains(new_state)) {
                    Q.add(new_state);
                    V.add(new_state);
                }
            }
        }
        try {
            FileWriter writer = new FileWriter("x:\\alaki.txt");
            String[] arr = V.toArray(new String[0]);
            Arrays.sort(arr);
            for (String s : arr)
                writer.write(s + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
