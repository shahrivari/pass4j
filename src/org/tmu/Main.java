package org.tmu;

import org.tmu.examples.EightPuzzle;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        // write your code here

        EightPuzzle rs = EightPuzzle.makeRandom(0);
        rs = rs.moveLeft();
        rs = rs.moveLeft();


        HashSet<AStarState> open_set = new HashSet<AStarState>();
        HashSet<AStarState> close_set = new HashSet<AStarState>();
        PriorityQueue<AStarState> Q = new PriorityQueue<AStarState>();
        Q.add(EightPuzzle.makeRandom(1));
        while (!Q.isEmpty()) {
            AStarState best = Q.poll();
            if (best.distanceToGoal() == 0.0)
                break;
            System.out.println(best);
            close_set.add(best);
            Collection<SearchState> neighbors = best.getNeighbors();
            for (SearchState neighbor : neighbors)
                if (close_set.contains(neighbor) || open_set.contains(neighbor))
                    continue;
                else {
                    Q.add((AStarState) neighbor);
                    open_set.add((AStarState) neighbor);
                }
        }

    }
}
