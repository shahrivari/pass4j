package org.tmu;

import org.tmu.examples.EightPuzzle;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        // write your code here

        HashSet<AStarState> open_set = new HashSet<AStarState>();
        HashSet<AStarState> close_set = new HashSet<AStarState>();
        PriorityQueue<AStarState> Q = new PriorityQueue<AStarState>();
        Q.add(EightPuzzle.makeRandom(1));
        while (!Q.isEmpty()) {
            AStarState best = Q.poll();
            if (best.getH() == 0.0)
                break;
            System.out.println(best);
            close_set.add(best);
            Collection<AStarState> neighbors = best.getAllNeighbors();
            for (AStarState neighbor : neighbors)
                if (close_set.contains(neighbor) || open_set.contains(neighbor))
                    continue;
                else {
                    Q.add(neighbor);
                    open_set.add(neighbor);
                }
        }

    }
}
