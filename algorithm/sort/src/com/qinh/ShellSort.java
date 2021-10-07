package com.qinh;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-03-21:26
 */
public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        //shellSort(arr);
        shellSort2(arr);
        long end = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");

    }

    /**
     * 希尔排序 交换方式
     * @param arr
     */
    private static void shellSort(int[] arr){
        int temp;
        boolean flag = false;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共gap组)，步长gap
                for (int j = i - gap; j >=0; j -=gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]){
                        flag = true;
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                    if (!flag){
                        break;
                    }else {
                        flag = false;
                    }
                }
            }
            //System.out.println("每组排序结果: " + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序 移位方式
     * @param arr
     */
    private static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                //前面的一个数比后面的数大，说明需要插入数据
                if (arr[j - gap] > arr[j]){
                   while ((j - gap) >= 0 && arr[j - gap] > temp){
                       //移动
                       arr[j] = arr[j - gap];
                       j -= gap;
                   }
                   //当退出while后，就给temp找到插入的位置
                   arr[j] = temp;
                }
            }
        }
    }
}
