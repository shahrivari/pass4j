package amu.saeed.pass4j;

import java.util.List;

/**
 * Created by Saeed on 12/23/2014.
 */
public interface SearchState {
    public SearchState undo();

    public double distanceFromRoot();

    public List<SearchState> getNeighbors();
}
