package com.qinh;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-04-0:23
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {-9,78,0,23,-567,70,2,4,-7};
        //int[] arr = {-9,78,0,23,-567,70};
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        long start = System.currentTimeMillis();
       //quickSort(arr, 0, arr.length - 1);
        quickSort2(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
        //System.out.println(Arrays.toString(arr));

    }


    /**
     * 先将中轴值保存在临时变量中
     * 先从后往前移动j,直到找到值比中轴值小的数，如果找到，将找到的值放入i位置，暂停移动，开始移动i
     * 然后从前往后移动i,直到找到值比中轴值大的数，如果找到，将找到的值放入j位置，暂停移动，开始移动j
     * 以此类推，直到 i == j,然后再把中轴值放入 i和j相等的位置处
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort2(int[] arr,int left,int right){
        //int[] arr = {-9,78,0,23,-567,70,2,4,-7},取第一个数为中轴 i = 0,j = arr.length - 1
        if (left < right){
            int pivot = arr[left];
            int i = left;
            int j = right;
            while (i < j){
                //先移动j
                while (i < j && arr[j] >= pivot){
                    j--;
                }
                //表明找到了小于等于pivot值的数
                arr[i] = arr[j];
                //再移动i
                while (i < j && arr[i] <= pivot){
                    i++;
                }
                //表明找到了大于等于pivot值的数
                arr[j] = arr[i];
            }
            //如果 i == j,将中轴值赋给 i == j的位置
            arr[i] = pivot;
            //左子区间排序
            quickSort2(arr, left, i - 1);
            //右子区间排序
            quickSort2(arr, i + 1, right);
        }
    }

    /**
     * 以下标中间值作为中轴
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort(int[] arr,int left,int right){
        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴
        int pivot = arr[(left + right) / 2];
        //临时变量，做为交换时使用
        int temp;
        //while循环的目的是让比pivot值小的放到左边，比pivot值大的放到右边
        while (l < r){
            //在pivot的左边一直找，直到找到大于等于pivot值，才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找，直到找到小于等于pivot的值，才退出
            while (arr[r] > pivot){
                r -= 1;
            }

            //说明pivot左右两边的值已经按照左边全部是小于等于pivot值，右边全部都是大于等于pivot的值
            if (l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值，r--,前移
            if (arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值，l++,后移
            if (arr[r] == pivot){
                l += 1;
            }
        }

        //如果 l == r,必须 l++,r--,否则出现栈溢出
        if (l == r){
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r){
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l){
            quickSort(arr, l, right);
        }
    }
}

class Solution {
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        long start = System.currentTimeMillis();
        //quickSort(new int[]{39,28,55,87,66,3,17,39});
        quickSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
    }

    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
        //System.out.println(Arrays.toString(arr));
    }
    public static void quickSort(int[] arr,int left,int right){
        int middle;
        if(left < right){
            middle = partition(arr,left,right);
            quickSort(arr,left,middle-1);
            quickSort(arr,middle+1,right);
        }
    }

    public static int partition(int[] arr,int left,int right){
        int pivot = arr[left];
        while(left < right){
            while(left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }
}
