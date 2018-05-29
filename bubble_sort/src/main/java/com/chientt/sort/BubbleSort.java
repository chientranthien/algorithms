package com.chientt.sort;

import java.util.Arrays;

public class BubbleSort<T> implements ArraySort<T> {

    @Override
    public T[] sort(T[] input, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(input, input.length);
        for (int i = input.length; i > 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (comparator.compare(result[j + 1], result[j])) {
                    swap(result, j, j + 1);
                }
            }
        }
        return result;
    }

    private void swap(T[] input, int index1, int index2) {
        T tmp = input[index1];
        input[index1] = input[index2];
        input[index2] = tmp;

    }

}
