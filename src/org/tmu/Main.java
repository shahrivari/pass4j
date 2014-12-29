package org.tmu;

import org.tmu.examples.TilePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        // write your code here
        double max_f = 33;
        String url = "ftp://ftp.cs.princeton.edu/pub/cs226/8puzzle";
        HashSet<TilePuzzle> open_set = new HashSet<TilePuzzle>();
        HashSet<TilePuzzle> close_set = new HashSet<TilePuzzle>();
        PriorityQueue<TilePuzzle> Q = new PriorityQueue<TilePuzzle>();
        TilePuzzle.goal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
        //TilePuzzle init = TilePuzzle.makeRandom(10, System.nanoTime());
        //TilePuzzle init=TilePuzzle.makeFromString("6,0,5,8,7,4,3,2,1");
//        TilePuzzle init=TilePuzzle.makeFromString("5  8  7 \n" +
//                " 1  4  6 \n" +
//                " 3  0  2");
        TilePuzzle init = TilePuzzle.makeFromString("5  8  7 \n" +
                " 1  4  6 \n" +
                " 3  0  2");

        //TilePuzzle init=TilePuzzle.makeFromString("13,1,0,4,8,12,6,3,15,9,5,14,0,7,2,11");
        //TilePuzzle init = TilePuzzle.makeFromString("11,0,4,7,2,15,1,8,5,14,9,3,13,6,12,10");
//        TilePuzzle init=TilePuzzle.makeFromString("3  1  6  4 \n" +
//                " 5  0  9  7 \n" +
//                "10  2 11  8 \n" +
//                "13 15 14 12");

        BfsSolver.solve(init, 30);
        System.exit(0);

        Q.add(init);
        while (!Q.isEmpty()) {
            TilePuzzle best = Q.poll();
            if (Q.size() % 100 == 0)
                System.out.println(Q.size());
            double best_f = best.fitness();
            //System.out.println(best);
            if (best.distanceToGoal() == 0.0) {
                System.out.println("#Moves: " + best.getActions().size());
                System.out.println(Arrays.toString(best.getActions().toArray()));
                break;
            }
            close_set.add(best);

            for (TilePuzzle.Action act : TilePuzzle.Action.values()) {
                TilePuzzle new_state = best.clone().move(act);
                if (new_state == null)
                    continue;
                if (new_state.fitness() > max_f)
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
