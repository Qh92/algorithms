package com.qinh;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，使用二分查找的前提，该数组是有序的
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-04-21:53
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1000,1000,1000,1000,1234};
        //int resultIndex = binarySearch(arr, 0, arr.length - 1, 89);
        List<Integer> resultIndex = binarySearch2(arr, 0, arr.length - 1, 1);
        System.out.println(resultIndex);
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param left 左边的数组
     * @param right 右边的数组
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    private static int binarySearch(int[] arr,int left,int right,int findVal){
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

    private static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        //当left > right时，说明递归整个数组，但是没有找到，防止死递归
        if (left > right){
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        //向右递归
        if (findVal > midVal){
            return binarySearch2(arr, mid + 1, right, findVal);
        }
        //向左递归
        else if (findVal < midVal){
            return binarySearch2(arr, left, mid - 1, findVal);
        }else {
            List<Integer> resultList = new ArrayList<>();
            //向mid索引值的左边扫描，将所有满足值的元素的下标，加入到集合中
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                //退出
                resultList.add(temp);
                temp--;
            }
            resultList.add(mid);
            //向mid索引的右边扫描，将所有满足值得元素得下标，加入到集合中
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                resultList.add(temp);
                temp++;
            }
            return resultList;
        }
    }


}
