package org.example.sort;


import org.example.HeapSort;
import org.example.QuickSort;
import org.example.Sorter;

import java.util.Arrays;

public class AdvancedSortingTest {
    public static void main(String[] args) {
        Integer[] array1 = {12, 11, 13, 5, 6, 7};
        Integer[] array2 = {12, 11, 13, 5, 6, 7};

        AdvancedSorter heapSorter = new AdvancedHeapSort();
        AdvancedSorter quickSorter = new AdvancedQuickSort();

        Integer[] sortedByHeap = heapSorter.advancedSort(array1);
        Integer[] sortedByQuick = quickSorter.advancedSort(array2);

        System.out.println("Heap Sorted array: " + Arrays.toString(sortedByHeap));
        System.out.println("Quick Sorted array: " + Arrays.toString(sortedByQuick));
        System.out.println(Arrays.equals(sortedByHeap, sortedByQuick));
    }
}

