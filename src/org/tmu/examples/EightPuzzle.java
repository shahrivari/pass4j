package org.tmu.examples;

import org.tmu.AStarState;
import org.tmu.SearchState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Saeed on 12/23/2014.
 */
public class EightPuzzle extends AStarState implements Comparable<EightPuzzle> {
    EightPuzzle parent = null;
    int[] arrangement = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    int numberOfExpands = 0;
    int displacements = 0;

    private EightPuzzle() {
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrangement);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EightPuzzle))
            return false;
        if (obj == this)
            return true;

        EightPuzzle that = (EightPuzzle) obj;
        return Arrays.equals(arrangement, that.arrangement);
    }

    @Override
    public EightPuzzle clone() {
        EightPuzzle new_state = new EightPuzzle();
        new_state.arrangement = arrangement.clone();
        new_state.displacements = displacements;
        new_state.numberOfExpands = numberOfExpands;
        new_state.parent = parent;
        return new_state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        for (int i = 0; i < 3; i++)
            builder.append(arrangement[i]).append(',');
        builder.setLength(builder.length() - 1);
        builder.append('\n');
        for (int i = 3; i < 6; i++)
            builder.append(arrangement[i]).append(',');
        builder.setLength(builder.length() - 1);
        builder.append('\n');
        for (int i = 6; i < 9; i++)
            builder.append(arrangement[i]).append(',');
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    @Override
    public int compareTo(EightPuzzle o) {
        return Double.compare(fitness(), o.fitness());
    }

    @Override
    public double distanceToGoal() {
        return displacements;
    }

    @Override
    public SearchState getParent() {
        return parent;
    }

    @Override
    public double distanceFromRoot() {
        return numberOfExpands;
    }

    private int setDisplacement() {
        displacements = 0;
        for (int i = 0; i < arrangement.length; i++)
            if (arrangement[i] != i)
                displacements++;
        return displacements;
    }

    public static EightPuzzle makeRandom(long seed) {
        EightPuzzle result = new EightPuzzle();
        Random rnd = new Random(seed);
        for (int i = result.arrangement.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = result.arrangement[index];
            result.arrangement[index] = result.arrangement[i];
            result.arrangement[i] = a;
        }
        result.setDisplacement();
        return result;
    }

    private int z_index() {
        int z_index = 0;
        for (; z_index < arrangement.length; z_index++)
            if (arrangement[z_index] == 0)
                break;
        return z_index;
    }

    public EightPuzzle moveLeft() {
        EightPuzzle new_state = clone();
        int z_index = z_index();
        if (z_index % 3 == 0)
            return null;
        new_state.arrangement[z_index] = new_state.arrangement[z_index - 1];
        new_state.arrangement[z_index - 1] = 0;
        new_state.numberOfExpands++;
        return new_state;
    }

    public EightPuzzle moveRight() {
        EightPuzzle new_state = clone();
        int z_index = z_index();
        if (z_index % 3 == 2)
            return null;
        new_state.arrangement[z_index] = new_state.arrangement[z_index + 1];
        new_state.arrangement[z_index + 1] = 0;
        new_state.numberOfExpands++;
        return new_state;
    }


    @Override
    public List<SearchState> getNeighbors() {
        List<SearchState> neighbors = new ArrayList<SearchState>();
        for (int i = 0; i < arrangement.length; i++) {
            EightPuzzle state = clone();
            int index = (i + 1) % arrangement.length;
            int a = state.arrangement[index];
            state.arrangement[index] = state.arrangement[i];
            state.arrangement[i] = a;
            state.numberOfExpands++;
            neighbors.add(state);
        }
        return neighbors;
    }

}
