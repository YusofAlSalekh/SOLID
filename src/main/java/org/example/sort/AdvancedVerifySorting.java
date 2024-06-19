package org.example.sort;

import java.util.Arrays;
import static jbse.meta.Analysis.ass3rt;

public class AdvancedVerifySorting {
    AdvancedSorter quickSorter = new AdvancedQuickSort();
    AdvancedSorter heapSorter = new AdvancedHeapSort();

    public void verifyAdvancedSort(Integer[] input) {
        Integer[] sortedByHeap = heapSorter.advancedSort(input);
        Integer[] sortedByQuick = quickSorter.advancedSort(input);
        ass3rt(!Arrays.equals(sortedByQuick, sortedByHeap));
    }
}
