package com.qinh.normalsort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-04-13:33
 */
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {8,4,5,7,1,3,6,2};

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        int[] temp = new int[arr.length];
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
    }

    /**
     * 分+合方法
     * @param arr 原始数组
     * @param left 左边序列的初始下标
     * @param right 右边序列的初始下标
     * @param temp 临时数组
     */
    private static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            //向左递归进行拆分
            mergeSort(arr, left, mid, temp);
            //向右递归进行拆分
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left,mid, right, temp);
        }
    }

    /**
     * 合并
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 临时数组
     */
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //左序列的初始下标
        int i = left;
        //右序列的初始下标
        int j = mid + 1;
        //临时数组的下标
        int t = 0;

        //左右序列进行大小比较，小的数将其填充至临时数组对应下标位置处
        while (i <= mid && j <= right){
            //左序列下标的值小一点
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }
            //右序列下标的值小一点
            else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //将剩余一个序列还未填充至临时数组的数据全部依次填充进临时数组中
        //左序列还有数据需要填充进数组中
        while (i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }
        //右序列还有数据需要填充进数组中
        while (j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将临时数组的数据复制进原始数组
        t = 0;
        int index = left;
        while (index <= right){
            arr[index] = temp[t];
            t++;
            index++;
        }

    }

}
