package amu.saeed.pass4j.simple;

import java.util.*;

/**
 * Created by Saeed on 7/25/2015.
 */
public class BreadthFirstSearch {

    public List<State> solve(State root, State goal) {
        Queue<State> queue = new ArrayDeque<State>();
        Set<State> tried = new HashSet<State>();
        if (goal.equals(root))
            return buildResult(root);

        queue.add(root);
        tried.add(root);
        while (queue.size() > 0) {
            State top = queue.poll();
            Collection<State> children = top.expand();
            for (State child : children) {
                if (child.equals(goal))
                    return buildResult(child);
                if (!tried.contains(child)) {
                    queue.add(child);
                    tried.add(child);
                }
            }
        }

        return null;
    }

    public List<State> buildResult(State last) {
        List<State> result = new ArrayList<State>();
        State current = last;
        while (current != null) {
            result.add(current);
            current = current.getParent();
        }
        Collections.reverse(result);
        return result;
    }
}
