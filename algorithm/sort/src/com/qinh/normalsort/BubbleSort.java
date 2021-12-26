package com.qinh.normalsort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-22:04
 */
public class BubbleSort {

    public static void main(String[] args) {
        //int[] arr = {3,9,-1,10,-2};
        //冒泡排序时间测试
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
        //System.out.println("最终排序结果:" + Arrays.toString(arr));
    }


    /**
     * 冒泡排序时间复杂度O(n^2)
     * @param arr
     */
    private static void bubbleSort(int[] arr){
        //临时变量
        int temp;
        //标识变量，表示是否进行过交换
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            //在一趟排序中，一次交换都没有发生过
            if (!flag){
                break;
            }
            //重置flag,进行下次判断
            else {
                flag = false;
            }
            /*System.out.println("第"+(i+1)+"躺排序后的数组");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
