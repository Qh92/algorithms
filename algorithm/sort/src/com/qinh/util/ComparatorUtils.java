package com.qinh.util;

import java.util.Arrays;

/**
 * 对数器
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-02 22:07
 */
public class ComparatorUtils {

    /**
     * 产生一个随机数组
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize,int maxValue) {
        /*
        Math.random() -> [0,1) 所有的小数，等概率返回一个
        Math.random() * N -> [0,N) 所有小数，等概率返回一个
        (int)(Math.random() * N) -> [0,N-1] 所有整数，等概率返回一个
         */
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

    /**
     * 对数器
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
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
    }
}
