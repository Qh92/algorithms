package com.qinh;

import java.util.Arrays;

/**
 * 插值查找
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-05-13:39
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        /*int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/
        //System.out.println(Arrays.toString(arr));

        int[] arr = {1,8,10,89,1000,1000,1000,1000,1000,1234,9999999};

        int i = insertValueSearch(arr, 9999999);
        //int i = binarySearch(arr, 0, arr.length - 1, 9999999);
        System.out.println(i);

    }

    private static int insertValueSearch(int[] arr,int findVal){
        return insertValueSearch(arr, 0, arr.length - 1, findVal);
    }

    private static int insertValueSearch(int[] arr,int left,int right,int findVal){
        System.out.println("插值查找");
        //注意：findVal < arr[0] || findVal > arr[arr.length - 1] 必须需要，有可能得到的mid越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        //求出mid，自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal){
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if (findVal < midVal){
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }

    }

    private static int binarySearch(int[] arr,int left,int right,int findVal){
        System.out.println("二分查找");
        //当left > right时，说明递归整个数组，但是没有找到，防止死递归
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        //向右递归
        if (findVal > midVal){
            return binarySearch(arr, mid + 1, right, findVal);
        }
        //向左递归
        else if (findVal < midVal){
            return binarySearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }
    }


}
