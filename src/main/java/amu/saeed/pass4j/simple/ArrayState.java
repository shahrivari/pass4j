package amu.saeed.pass4j.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Saeed on 7/25/2015.
 */
public class ArrayState implements State {
    int[] array;
    ArrayState parent = null;

    public ArrayState(int[] arr) {
        array = arr;
    }

    public ArrayState(int[] arr, ArrayState parent) {
        array = arr;
        this.parent = parent;
    }


    public State getParent() {
        return parent;
    }

    public Collection<State> expand() {
        List<State> result = new ArrayList<State>();
        for (int i = 0; i < array.length; i++)
            for (int j = i + 1; j < array.length; j++) {
                int[] arr = array.clone();
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                result.add(new ArrayState(arr, this));
            }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof ArrayState))
            return false;

        ArrayState other = (ArrayState) obj;
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
