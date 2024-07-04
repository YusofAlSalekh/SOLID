package org.example.sorting;

import org.example.HeapSort;
import org.example.QuickSort;
import org.example.Sorter;

import java.util.Arrays;

import static jbse.meta.Analysis.ass3rt;
import static jbse.meta.Analysis.assume;

public class VerifySorting {
    private static final Sorter quickSort = new QuickSort();
    private static final Sorter heapSort = new HeapSort();

    public static void verify(int[] array) {
        assume(array.length <= 5);

        int[] sortByQuick = quickSort.sort(Arrays.copyOf(array, array.length));
        int[] sortByHeap = heapSort.sort(Arrays.copyOf(array, array.length));

        ass3rt(Arrays.equals(sortByQuick, sortByHeap));
    }
}



