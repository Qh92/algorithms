package com.qinh.zcy.class03;

import java.util.PriorityQueue;

/**
 * 时间复杂度 O(N * logN)
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-08 23:40
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N)
        for (int i = 0; i < arr.length; i++) {
            // O(logN)
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        // O(N)
        while (heapSize > 0) {
            // O(logN)
            heapify(arr,0,heapSize);
            // O(1)
            swap(arr,0,--heapSize);
        }
    }


    // 某个数现在处在index位置，往上继续移动
    public static void heapInsert(int[] arr,int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 某个数在index位置，能否往下移动
    public static void heapify(int[] arr,int index,int heapSize) {
        // 左孩子的下标
        int left = index * 2 + 1;
        // 下方还有孩子的时候
        while(left < heapSize) {
            // 两个孩子中，谁的值大，把下标给largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index) {
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


}
