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
        quickSort(result, 0, input.length - 1, comparator);
        return result;
    }

    private void quickSort(T[] input,
            int head,
            int tail,
            Comparator<T> comparator) {
        pivot(input, head, tail, comparator);
        if (tail <= head) {
            return;
        }
        int med = (head + tail) / 2;
        quickSort(input, head, med, comparator);
        quickSort(input, med + 1, tail, comparator);
    }

    public void pivot(T[] input, int index1, int index2, Comparator<T> comparator) {
        int pivotIndex = index2;
        T pivotVal = input[pivotIndex];
        int i = pivotIndex;
        while (i >= index1) {

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
    }

}
