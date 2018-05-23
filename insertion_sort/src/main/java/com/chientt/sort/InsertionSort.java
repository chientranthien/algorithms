package com.chientt.sort;

import java.util.Arrays;

public class InsertionSort<T> implements ArraySort<T> {

    @Override
    public T[] sort(T[] input, Comparator comparator) {
        T[] result = Arrays.copyOf(input, input.length);
//test
        for (int i = 1; i < result.length; i++) {
            T value = result[i];
            for (int j = i - 1; j >= 0; j--) {
                if (comparator.compare(value, result[j])) {
                    result[j + 1] = result[j];
                } else {
                    result[j] = value;
                    break;
                }
            }
        }
        return result;
    }


}
