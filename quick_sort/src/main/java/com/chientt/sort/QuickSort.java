package com.chientt.sort;

import java.util.Arrays;

/**
 * @author chientt
 */
public class QuickSort<T> implements ArraySort<T> {

    @Override
    public T[] sort(T[] input, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(input, input.length);
        quickSort(result, 0, input.length - 1, comparator);
        return result;
    }

    private void quickSort(T[] input,
                           int low,
                           int high,
                           Comparator<T> comparator) {
        if (low >= high ) {
            return;
        }
        int pivotIndex = partition(input, low, high, comparator);
        quickSort(input, low, pivotIndex - 1, comparator);
        quickSort(input, pivotIndex + 1, high, comparator);

    }

    public int partition(T[] input, int low, int high, Comparator<T> comparator) {
        int pivotIndex = high;
        T pivotVal = input[pivotIndex];
        int i = pivotIndex - 1;
        while (i >= low) {

            if (comparator.compare(pivotVal, input[i])) {
                T tmp = input[i];
                for (int j = i; j < pivotIndex; j++) {
                    input[j] = input[j + 1];
                }
                input[pivotIndex] = tmp;
                pivotIndex--;
            }
            i--;
        }
        return pivotIndex;
    }

}
