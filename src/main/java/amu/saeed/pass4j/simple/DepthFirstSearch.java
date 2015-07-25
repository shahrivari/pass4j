package amu.saeed.pass4j.simple;

import java.util.*;

/**
 * Created by Saeed on 7/25/2015.
 */
public class DepthFirstSearch {

    public List<State> solve(State root, State goal) {
        Stack<State> stack = new Stack<State>();
        Set<State> tried = new HashSet<State>();
        if (goal.equals(root))
            return buildResult(root);

        stack.push(root);
        tried.add(root);
        while (!stack.empty()) {
            State top = stack.pop();
            Collection<State> children = top.expand();
            for (State child : children) {
                if (child.equals(goal))
                    return buildResult(child);
                if (!tried.contains(child)) {
                    stack.push(child);
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
