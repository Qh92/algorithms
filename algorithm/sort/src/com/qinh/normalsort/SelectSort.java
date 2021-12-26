package com.qinh.normalsort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-22:56
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1,-1,89,122};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");

    }

    private static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值，放在arr[i]，即交换
            if (minIndex != 0){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            /*System.out.println("第"+(i+1)+"轮");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
