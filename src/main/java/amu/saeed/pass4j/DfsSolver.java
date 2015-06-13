package amu.saeed.pass4j;

import amu.saeed.pass4j.examples.TilePuzzle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Saeed on 12/28/2014.
 */
public class DfsSolver {

    static HashSet<String> visited = new HashSet<String>();

    public static TilePuzzle rolve(TilePuzzle state, final double max_f) {
        visited.add(state.toString());
        if (state.distanceToGoal() == 0) {
            System.out.println("#Visited: " + visited.size());
            System.out.println("#Moves: " + state.getActions().size());
            System.out.println(Arrays.toString(state.getActions().toArray()));
            return state;
        }
        if (state.getActions().size() > max_f)
            return null;
        for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
            TilePuzzle new_state = state.clone().move(act);
            if (new_state == null)
                continue;
            if (!visited.contains(new_state.toString()))
                rolve(new_state, max_f);
        }
        return null;
    }

    public static TilePuzzle solve(TilePuzzle init_state, final double max_f) {
        Stack<TilePuzzle> S = new Stack<TilePuzzle>();
        HashSet<String> V = new HashSet<String>();
        S.add(init_state);
        while (!S.isEmpty()) {
            TilePuzzle top = S.pop();

            if (top.distanceToGoal() == 0) {
                System.out.println("#Visited: " + V.size());
                System.out.println("#Moves: " + top.getActions().size());
                System.out.println(Arrays.toString(top.getActions().toArray()));
                return top;
            }
            if (top.getActions().size() > max_f)
                continue;

            if (!V.contains(top.toString())) {
                V.add(top.toString());
                for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
                    TilePuzzle new_state = top.clone().move(act);
                    if (new_state == null)
                        continue;
                    S.push(new_state);
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
