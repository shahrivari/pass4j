package org.tmu;

import java.util.List;

/**
 * Created by Saeed on 12/23/2014.
 */
public interface SearchState {

    public SearchState revert();
    public double distanceFromRoot();//current cost
    public List<SearchState> getNeighbors();
}
