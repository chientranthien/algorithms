package com.chientt.sort;

import java.util.Arrays;

public class InsertionSort<T> implements ArraySort<T> {

    @Override
    public T[] sort(T[] input, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(input, input.length);
        for (int i = 1; i < result.length; i++) {
            T value = result[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(value, result[j])) {
                result[j + 1] = result[j];
                j--;
            }
            result[++j] = value;
        }
        return result;
    }


}
