package com.chientt.sort;

import java.util.Arrays;

public class MergeSort<T> implements ArraySort<T> {
    
    @Override
    public T[] sort(T[] input, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(input, input.length);
        mergeSort(result, 0, result.length - 1, comparator);
        return result;
    }
    
    private void mergeSort(T[] input,
            int fromIndex,
            int toIndex,
            Comparator<T> comparator) {
        if (fromIndex >= toIndex) {
            return;
        }
        int mid = (fromIndex + toIndex) / 2;
        mergeSort(input, fromIndex, mid, comparator);
        mergeSort(input, mid + 1, toIndex, comparator);
        insertionSort(input, fromIndex, toIndex, comparator);
        
    }
    
    private void insertionSort(T[] input,
            int fromIndex,
            int toIndex,
            Comparator<T> comparator) {
        
        for (int i = fromIndex + 1; i <= toIndex; i++) {
            T value = input[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(value, input[j])) {
                input[j + 1] = input[j];
                j--;
            }
            input[++j] = value;
        }
        
    }
}
