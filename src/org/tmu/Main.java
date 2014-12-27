package org.tmu;

import org.tmu.examples.TilePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        // write your code here

        HashSet<TilePuzzle> open_set = new HashSet<TilePuzzle>();
        HashSet<TilePuzzle> close_set = new HashSet<TilePuzzle>();
        PriorityQueue<TilePuzzle> Q = new PriorityQueue<TilePuzzle>();
        TilePuzzle init = TilePuzzle.makeRandom(10, System.nanoTime());

        Q.add(init);
        while (!Q.isEmpty()) {
            TilePuzzle best = Q.poll();
            double best_f = best.fitness();
            System.out.println(best);
            if (best.distanceToGoal() == 0.0) {
                System.out.println(Arrays.toString(best.getActions().toArray()));
                break;
            }
            close_set.add(best);

            for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
                TilePuzzle new_state = best.clone().move(act);
                ;
                if (new_state == null)
                    continue;
                if (close_set.contains(new_state) || open_set.contains(new_state))
                    continue;
                else {
                    Q.add(new_state);
                    open_set.add(new_state);
                }
            }
        }

    }
}
