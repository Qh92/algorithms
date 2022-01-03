package com.qinh.zcy.class02.mergesort;

import java.util.Arrays;

/**
 * 归并排序：
 * 选定一个基数，将基数的左右两边的数据分别进行排序，利用一个额外空间数组，依次取出左右两边的数并进行比较
 * 将较小值放入额外空间数组中，最后将额外空间数组数据拷贝至原来的数组中
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-02 15:23
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1,2,10,20,3,9,6,40,2,6,7,8,32};
        process(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


    public static void process(int[] arr,int L,int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr,L, mid);
        process(arr, mid + 1, R);
        merge(arr,L,mid,R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }
}
