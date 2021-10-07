package com.qinh;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-04-18:13
 */
public class RadixSort {

    public static void main(String[] args) {
        //int[] arr = {53,3,542,748,14,214,88,66,24,43,98};
        int[] arr = new int[80000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        int[] temp = new int[arr.length];
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        System.out.println("排序执行时间: " + (end-start) + "ms");
        System.out.println("排序执行时间: " + ((end-start) / 1000) + "s");
    }

    private static void radixSort(int[] arr){

        //最终的基数排序
        //假设第一位数就是最大数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        //最大数是几位数
        int maxLength = ("" + max).length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int z = 0,n = 1; z < maxLength; z++,n *= 10) {
            //针对每个元素的位数进行排序处理，第一次是个位，第二次是十位...
            for (int value : arr) {
                //取出每个数的个位数
                int digitOfElement = value / n % 10;
                //将值放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一个桶，并将桶中数据，放入到原来数组
            for (int i = 0; i < bucketElementCounts.length; i++) {
                //桶中放入了数据，才从当前桶中取出数据
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
                //取完数据后，需要将每个bucketElementCounts计数计为0
                bucketElementCounts[i] = 0;
            }
        }



        /*
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶放入的数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮（针对每个元素的个位进行排序处理）
        for (int value : arr) {
            //取出每个数的个位数
            int digitOfElement = value % 10;
            //将值放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        int index = 0;
        //遍历每一个桶，并将桶中数据，放入到原来数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //桶中放入了数据，才从当前桶中取出数据
            for (int j = 0; j < bucketElementCounts[i]; j++) {
                arr[index] = bucket[i][j];
                index++;
            }
            //取完数据后，需要将每个bucketElementCounts计数计为0
            bucketElementCounts[i] = 0;
        }

        //第二轮（针对每个元素的十位进行排序处理）
        for (int value : arr) {
            //取出每个数的个位数
            int digitOfElement = value / 10 % 10 ;
            //将值放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中数据，放入到原来数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //桶中放入了数据，才从当前桶中取出数据
            for (int j = 0; j < bucketElementCounts[i]; j++) {
                arr[index] = bucket[i][j];
                index++;
            }
            //取完数据后，需要将每个bucketElementCounts计数计为0
            bucketElementCounts[i] = 0;
        }

        //第三轮（针对每个元素的百位进行排序处理）
        for (int value : arr) {
            //取出每个数的个位数
            int digitOfElement = value / 100 % 10 ;
            //将值放入对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标依次取出数据，放入原来数组）
        index = 0;
        //遍历每一个桶，并将桶中数据，放入到原来数组
        for (int i = 0; i < bucketElementCounts.length; i++) {
            //桶中放入了数据，才从当前桶中取出数据
            for (int j = 0; j < bucketElementCounts[i]; j++) {
                arr[index] = bucket[i][j];
                index++;
            }
            //取完数据后，需要将每个bucketElementCounts计数计为0
            bucketElementCounts[i] = 0;
        }*/
    }
}
