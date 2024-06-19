package org.example.sort;

public class AdvancedHeapSort implements AdvancedSorter {
    @Override
    public Integer[] advancedSort(Integer[] source) {
        Integer[] arr = source.clone();
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            advancedHeapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            Integer temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            advancedHeapify(arr, i, 0);
        }
        return arr;
    }

    private void advancedHeapify(Integer[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            Integer swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            advancedHeapify(arr, n, largest);
        }
    }
}
