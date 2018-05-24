package com.chientt.sort;

import java.util.Arrays;

/**
 *
 * @author chientt
 */
public class QuickSort<T> implements ArraySort<T> {

    @Override
    public T[] sort(T[] input, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(input, input.length);
        int head = 0;
        int tail = 0;
        int pivot = (head + tail) / 2;

        return result;
    }

    private void sort(T[] input,
            int tail,
            int pivot,
            int head,
            Comparator<T> comparator) {
        for (int i = pivot; i >= tail; i--) {

        }
    }

    public void pivot(T[] input, int index1, int index2, Comparator<T> comparator) {
        int pivotIndex = index2;
        T pivotVal = input[pivotIndex];
        int i = pivotIndex;
        while (i >= index1) {
            if (comparator.compare(pivotVal, input[i])) {
                for (int j = pivotIndex - 1; j >= i; j--) {
                    input[j+1] = input[j];
                }
                pivotIndex = i;
                input[pivotIndex] = pivotVal;
            }
            i--;
        }
    }

}
