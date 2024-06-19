

package org.example;

import java.util.Arrays;

import static jbse.meta.Analysis.ass3rt;
import static jbse.meta.Analysis.assume;

public class VerifyArrayHandling {

    public void verify(int[] array) {
        assume(array.length <= 3);

        int[] inputForQuickSort = new int[array.length];
        int[] inputForHeapSort  = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            inputForQuickSort[i] = array[i];
        }
        for (int i = 0; i < array.length; i++) {
            inputForHeapSort[i] = array[i];
        }

        int[] sortedByQuick = sortQuick(inputForQuickSort);
        int[] sortedByHeap = sortHeap(inputForHeapSort);

        ass3rt(Arrays.equals(sortedByQuick, sortedByHeap));
    }

   /*public void verify() {
       int[] array = {12, 11, 13, 5, 6, 7};

       int[] inputForQuickSort = array.clone();
       int[] inputForHeapSort = array.clone();

       int[] sortedByQuick = sortQuick(inputForQuickSort);
       int[] sortedByHeap =  sortHeap(inputForHeapSort);

       System.out.println(Arrays.toString(sortedByHeap));
       System.out.println(Arrays.toString(sortedByQuick));

       ass3rt(Arrays.equals(sortedByQuick,sortedByHeap));
   }*/

    public int[] sortQuick(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
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

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    ///////////////////////////////////////////////////////
    public int[] sortHeap(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
        return arr;
    }

    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}
