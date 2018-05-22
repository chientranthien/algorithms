package com.chientt.sort;

import org.junit.Test;

public class IntegerSortTests {

    @Test
    public void test() {
        Integer input[] = new Integer[]{3, 1, 6, 17, 5, 1};
        ArraySort<Integer> arraySort = new InsertionSort<Integer>();
        Comparator<Integer> comparator=new Comparator<Integer>() {
            @Override
            public boolean compare(Integer value1, Integer value2) {
                return value1>value2;
            }
        };
        Integer[] sort = arraySort.sort(input, comparator);

        for (Integer integer : sort) {
            System.out.println(integer);
        }
    }
}
