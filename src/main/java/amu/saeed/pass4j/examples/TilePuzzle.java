package amu.saeed.pass4j.examples;

import amu.saeed.pass4j.SearchState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Saeed on 12/23/2014.
 */
public class TilePuzzle implements Comparable<TilePuzzle> {
    public static int[] goal;
    int dimension = -1;
    int[] arrangement;
    List<Action> actions = new ArrayList<Action>();
    int displacements = 0;

    private TilePuzzle(final int dimension) {
        this.dimension = dimension;
        arrangement = new int[dimension * dimension];
        for (int i = dimension * dimension - 1; i > 0; i--)
            arrangement[i] = i;
    }

    public static TilePuzzle makeFromString(String s) {
        s = s.replaceAll("[,\\s]+", " ");
        String[] tokens = s.split("\\s");
        TilePuzzle result = new TilePuzzle((int) Math.sqrt(tokens.length));
        for (int i = 0; i < tokens.length; i++)
            result.arrangement[i] = Integer.parseInt(tokens[i]);
        result.actions.clear();
        result.setDisplacement();
        return result;
    }

    public static TilePuzzle makeRandom(int dimension, long seed) {
        TilePuzzle result = new TilePuzzle(dimension);
        Random rnd = new Random(seed);
        while (result.distanceToGoal() == 0) {
            for (int i = rnd.nextInt(100); i > 0; i--)
                result.move(Action.values()[rnd.nextInt(Action.values().length)]);
        }
        result.actions.clear();
        result.setDisplacement();
        return result;
    }

    public List<Action> getActions() {
        return actions;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrangement);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TilePuzzle))
            return false;
        if (obj == this)
            return true;

        TilePuzzle that = (TilePuzzle) obj;
        return Arrays.equals(arrangement, that.arrangement);
    }

    @Override
    public TilePuzzle clone() {
        TilePuzzle new_state = new TilePuzzle(dimension);
        new_state.arrangement = arrangement.clone();
        new_state.displacements = displacements;
        new_state.actions.addAll(actions);
        return new_state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);

        for (int i = 0; i < arrangement.length; i++) {
            if (i % dimension == 0 && i > 0) {
                builder.setLength(builder.length() - 1);
                builder.append('|').append(arrangement[i]).append(',');
            } else
                builder.append(arrangement[i]).append(',');
        }
        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

    public int compareTo(TilePuzzle o) {
        return Double.compare(fitness(), o.fitness());
    }

    public double fitness() {
        return distanceFromRoot() + distanceToGoal();
    }

    public double distanceToGoal() {
        return displacements;
    }

    public double distanceFromRoot() {
        return actions.size();
    }

    private int setDisplacement() {
        displacements = 0;
        for (int i = 0; i < arrangement.length; i++)
            if (arrangement[i] != goal[i])
                displacements++;
        return displacements;
    }

    private int z_index() {
        int z_index = 0;
        for (; z_index < arrangement.length; z_index++)
            if (arrangement[z_index] == 0)
                break;
        return z_index;
    }

    public TilePuzzle move(Action action) {
        int z_index = z_index();
        if (action == Action.LEFT) {
            if (z_index % dimension == 0)
                return null;
            arrangement[z_index] = arrangement[z_index - 1];
            arrangement[z_index - 1] = 0;
            actions.add(Action.LEFT);
            setDisplacement();
            return this;
        } else if (action == Action.RIGHT) {
            if (z_index % dimension == dimension - 1)
                return null;
            arrangement[z_index] = arrangement[z_index + 1];
            arrangement[z_index + 1] = 0;
            actions.add(Action.RIGHT);
            setDisplacement();
            return this;
        } else if (action == Action.UP) {
            if (z_index < dimension)
                return null;
            arrangement[z_index] = arrangement[z_index - dimension];
            arrangement[z_index - dimension] = 0;
            actions.add(Action.UP);
            setDisplacement();
            return this;
        } else if (action == Action.DOWN) {
            if (z_index > (dimension * dimension - dimension - 1))
                return null;
            arrangement[z_index] = arrangement[z_index + dimension];
            arrangement[z_index + dimension] = 0;
            actions.add(Action.DOWN);
            setDisplacement();
            return this;
        } else throw new IllegalArgumentException("Bad move.");
    }

    public List<SearchState> getNeighbors() {
        List<SearchState> neighbors = new ArrayList<SearchState>();
        return neighbors;
    }


    public static enum Action {LEFT, RIGHT, UP, DOWN}

}
