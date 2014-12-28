package org.tmu;

import org.tmu.examples.TilePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by Saeed on 12/28/2014.
 */
public class DfsSolver {

    public static TilePuzzle solve(TilePuzzle init_state, final double max_f) {
        Stack<TilePuzzle> S = new Stack<TilePuzzle>();
        HashSet<TilePuzzle> visited = new HashSet<TilePuzzle>();
        S.add(init_state);
        visited.add(init_state);
        while (!S.isEmpty()) {
            TilePuzzle top = S.pop();
            if (top.distanceToGoal() == 0) {
                System.out.println("#Moves: " + top.getActions().size());
                System.out.println(Arrays.toString(top.getActions().toArray()));
                return top;
            }

//            if(top.fitness()>max_f)
//                continue;
            for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
                TilePuzzle new_state = top.clone().move(act);
                if (new_state == null)
                    continue;
                if (new_state.fitness() > max_f) {
                    continue;
                }
//                if (close_set.contains(new_state) || open_set.contains(new_state))
//                    continue;
                if (!visited.contains(new_state)) {
                    S.push(new_state);
                    visited.add(new_state);
                }

//                else {
//                    if(!visited.contains(new_state)) {
//                        visited.add(new_state);
//                    }
//                    open_set.add(new_state);
//                }
            }
        }
        return null;
    }
}
