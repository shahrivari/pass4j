package amu.saeed.pass4j.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Saeed on 7/25/2015.
 */
public class BubbleSortState implements State {
    int[] array;
    BubbleSortState parent = null;

    public BubbleSortState(int[] arr) {
        array = arr;
    }

    public BubbleSortState(int[] arr, BubbleSortState parent) {
        array = arr;
        this.parent = parent;
    }


    public State getParent() {
        return parent;
    }

    public Collection<State> expand() {
        List<State> result = new ArrayList<State>();
        for (int i = 0; i < array.length - 1; i++) {
            int j = i + 1;
            if (j >= array.length)
                break;
            int[] arr = array.clone();
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            result.add(new BubbleSortState(arr, this));
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof BubbleSortState))
            return false;

        BubbleSortState other = (BubbleSortState) obj;
        return Arrays.equals(array, other.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
