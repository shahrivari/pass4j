package org.tmu;

import java.util.Collection;

/**
 * Created by Saeed on 12/23/2014.
 */
public abstract class AStarState {

    AStarState parent = null;

    public AStarState getParent() {
        return parent;
    }

    public abstract double getG();//current cost

    public abstract double getH();//heuristical cost to goal

    public double getF() {
        return getG() + getH();
    } //the fitness function

    public abstract Collection<AStarState> getAllNeighbors();
}
