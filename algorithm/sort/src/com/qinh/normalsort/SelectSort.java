package com.qinh.normalsort;

import com.qinh.util.ComparatorUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 选择排序
 * 每一次遍历将最小或最大值放在前面
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-22:56
 */
public class SelectSort {
    public static void main(String[] args) {
        //int[] arr = {101,34,119,1,-1,89,122};
        /*int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");*/

        //对数器测试
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ComparatorUtils.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ComparatorUtils.copyArray(arr1);
            selectSort(arr1);
            ComparatorUtils.comparator(arr2);
            if (!ComparatorUtils.isEqual(arr1,arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fail!!");

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

    /**
     * 产生一个随机数组
     * @param maxSize
     * @param maxValue
     * @return
     *//*
    public static int[] generateRandomArray(int maxSize,int maxValue) {
        *//*
        Math.random() -> [0,1) 所有的小数，等概率返回一个
        Math.random() * N -> [0,N) 所有小数，等概率返回一个
        (int)(Math.random() * N) -> [0,N-1] 所有整数，等概率返回一个
         *//*
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    *//**
     * 对数器
     * @param arr
     *//*
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }*/
}
