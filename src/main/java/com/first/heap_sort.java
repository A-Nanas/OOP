package com.first;
public class heap_sort {
    /**
     * Support method for sort method; converts into binary heap
     * @param a the array, we're going to sort
     * @param n array length
     * @param i root node of the subtree, that we're going to convert
     */
    private static void prepare(int[] a, int n, int i) {
        int root = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && a[left] > a[root]) {
            root = left;
        }
        if (right < n && a[right] > a[root]) {
            root = right;
        }
        if (root != i) {
            int t = a[i];
            a[i] = a[root];
            a[root] = t;
            prepare(a, n, root);
        }
    }
    /**
     * Runs HeapSort algorithm
     * @param a unsorted array
     * @return sorted array
     */
    public static void sort(int[] a) {
        int len = a.length;
        for(int i = len / 2 - 1; i >= 0; i--) {
            prepare(a, len, i);
        }
        for (int i = len - 1; i >= 0; i--) {
            int t = a[0];
            a[0] = a[i];
            a[i] = t;
            prepare(a, i, 0);
        }
    }
}