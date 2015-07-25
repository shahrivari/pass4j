package amu.saeed.pass4j.simple;

import java.util.Collection;

/**
 * Created by Saeed on 7/25/2015.
 */
public interface State {
    public State getParent();

    public Collection<State> expand();
}
