package com.qinh.zcy.class02.quicksort;

/**
 * 快速排序3.0，时间复杂度O(n * log2^n)
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-02 19:29
 */
public class QuickSort {

    public static void quickSort(int[] arr,int L,int R) {
        if (L < R) {
            swap(arr,L + (int)(Math.random() * (R - L + 1)),R);
            int[] p = partition(arr,L,R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1,R);
        }
    }

    /**
     * 默认以arr[r]做划分，arr[r] -> p   <p  ==p >p
     * 返回等于区域(左边界，右边界) ,所以返回一个长度为2的数组res,res[0] res[1]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int[] partition(int[] arr, int l, int r) {
        // <区右边界
        int less = l - 1;
        // >区左边界
        int more = r;
        // l表示当前数的位置 arr[r] -> 划分值
        while (l < more) {
            // 当前数 < 划分值
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            }else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            }else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[] {less + 1,more};
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

}
