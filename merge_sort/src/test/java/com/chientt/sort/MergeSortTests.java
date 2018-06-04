package com.chientt.sort;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class MergeSortTests {

    @Test
    public void shouldSortAsc() {
        Integer input[] = new Integer[]{3, 1, 6, 17, 5, 1};
        Integer expectation[] = new Integer[]{1, 1, 3, 5, 6, 17};
        ArraySort<Integer> arraySort = new MergeSort<>();
        Integer[] result = arraySort.sort(
                input,
                (a, b) -> a < b);

        Assertions.assertThat(result).isEqualTo(expectation);
    }

    @Test
    public void shouldSortDesc() {
        Integer input[] = new Integer[]{3, 1, 6, 17, 5, 1};
        Integer expectation[] = new Integer[]{17, 6, 5, 3, 1, 1};
        ArraySort<Integer> arraySort = new MergeSort<>();
        Integer[] result = arraySort.sort(
                input,
                (a, b) -> a > b);

        Assertions.assertThat(result).isEqualTo(expectation);
    }
}
