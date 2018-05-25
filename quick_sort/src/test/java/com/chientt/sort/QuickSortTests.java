package com.chientt.sort;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class QuickSortTests {

    @Test
    public void shouldSortAsc() {
        Integer input[] = new Integer[]{3, 1, 6, 17, 5, 1};
        Integer expectation[] = new Integer[]{1, 1, 3, 5, 6, 17};
        ArraySort<Integer> arraySort = new QuickSort();
        Integer[] result = arraySort.sort(
                input,
                (a, b) -> a < b);

        Assertions.assertThat(result).isEqualTo(expectation);
    }

    @Test
    public void shouldSortDesc() {
        Integer input[] = new Integer[]{3, 1, 6, 17, 5, 1};
        Integer expectation[] = new Integer[]{17, 6, 5, 3, 1, 1};
        ArraySort<Integer> arraySort = new QuickSort();
        Integer[] result = arraySort.sort(
                input,
                (a, b) -> a > b);

        Assertions.assertThat(result).isEqualTo(expectation);
    }
    
    
//    @Test
//    public void testPivot() {
//        Integer input[] = new Integer[]{3, 1, 6, 17, 2, 4};
//        QuickSort<Integer> arraySort = new QuickSort();
//
////        arraySort.pivot(input, 0, input.length - 1, (a, b) -> a < b);
//        arraySort.sort(input, (a, b) -> a < b);
//        for (Integer integer : input) {
//            System.out.println(integer);
//        }
//
//    }
}
