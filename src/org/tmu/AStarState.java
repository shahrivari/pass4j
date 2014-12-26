package org.tmu;

/**
 * Created by Saeed on 12/27/14.
 */
public abstract class AStarState implements SearchState {
    public abstract double distanceToGoal();

    public double fitness() {
        return distanceFromRoot() + distanceToGoal();
    }
}
