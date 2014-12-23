package org.tmu.examples;

import org.tmu.AStarState;

import java.util.*;

/**
 * Created by Saeed on 12/23/2014.
 */
public class EightPuzzle extends AStarState implements Comparable<EightPuzzle> {
    int[] arrangement = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    int actions = 0;
    int displacement = 0;

    private EightPuzzle() {
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

    @Override
    public double getG() {
        return actions;
    }

    @Override
    public double getH() {
        return displacement;
    }

    private void setDisplacement() {
        displacement = 0;
        for (int i = 0; i < arrangement.length; i++)
            if (arrangement[i] != i)
                displacement++;
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
        new_state.displacement = displacement;
        new_state.actions = actions;
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

    EightPuzzle getLeft() {
        EightPuzzle state = clone();
        int z_index = 0;
        for (; z_index < arrangement.length; z_index++)
            if (arrangement[z_index] == 0)
                break;

        return state;
    }

    @Override
    public Collection<AStarState> getAllNeighbors() {
        List<AStarState> neighbors = new ArrayList<AStarState>();
        for (int i = 0; i < arrangement.length; i++) {
            EightPuzzle state = clone();
            int index = (i + 1) % arrangement.length;
            int a = state.arrangement[index];
            state.arrangement[index] = state.arrangement[i];
            state.arrangement[i] = a;
            state.actions++;
            neighbors.add(state);
        }
        return neighbors;
    }

    @Override
    public int compareTo(EightPuzzle o) {
        return Double.compare(getF(), o.getF());
    }
}
