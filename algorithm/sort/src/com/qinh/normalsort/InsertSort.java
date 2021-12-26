package com.qinh.normalsort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-03-13:54
 */
public class InsertSort {

    public static void main(String[] args) {
        //int[] arr = {101,34,119,1,-1,79};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
    }

    private static void insertSort(int[] arr){
        //定义待插入的数
        int insertVal = 0;
        //即arr[i]前面的这个数的小标
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            /*
            给insertVal找到插入的位置
            1.insertIndex >= 0 找插入的位置，不越界
            2.insertVal < arr[insertIndex] 找到插入的位置
            3.需要将arr[insertIndex]后移
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //说明找到插入的位置
            //判断是否需要赋值
            if (insertIndex +1 != i){
                arr[insertIndex + 1] = insertVal;
            }
            /*System.out.println("第"+i+"轮插入");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
