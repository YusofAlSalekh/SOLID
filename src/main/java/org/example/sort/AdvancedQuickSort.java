package org.example.sort;


public class AdvancedQuickSort implements AdvancedSorter {
    @Override
    public Integer[] advancedSort(Integer[] source) {
        Integer[] arr = source.clone();
       // advancedQuickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void advancedQuickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = advancedPartition(arr, low, high);
            advancedQuickSort(arr, low, pi - 1);
            advancedQuickSort(arr, pi + 1, high);
        }
    }

    private int advancedPartition(Integer[] arr, int low, int high) {
        Integer pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
