package org.example;

import java.util.Arrays;

import static jbse.meta.Analysis.ass3rt;
import static jbse.meta.Analysis.assume;

public class VerifySorting {
    private static final Sorter quickSort = new QuickSort();
    private static final Sorter heapSort = new HeapSort();

    public static void verify(int[] array) {
        assume(array.length <= 5);
        int[] inputForQuickSort = Arrays.copyOf(array, array.length);  // inpu5.clone()
        int[] inputForHeapSort = Arrays.copyOf(array, array.length);

        int[] sortByQuick = quickSort.sort(Arrays.copyOf(array, array.length));
        int[] sortByHeap = heapSort.sort(Arrays.copyOf(array, array.length));

        ass3rt(Arrays.equals(sortByQuick, sortByHeap));
    }
}



