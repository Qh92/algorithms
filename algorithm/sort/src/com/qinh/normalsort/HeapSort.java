package com.qinh.normalsort;

/**
 * 堆排序,时间复杂度:O(nlogn)
 * 大顶堆：每个结点的值都大于等于其左右孩子结点的值
 * 小顶堆：每个结点的值都小于等于其左右孩子结点的值
 *
 * @author Qh
 * @version 1.0
 * @date 2021-09-21-15:06
 */
public class HeapSort {

    public static void main(String[] args) {
//        int[] arr = {4,6,8,5,9,-1,-10,12,40,-90};
        int[] arr = new int[800_0000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 800_0000);
        }
        long start = System.currentTimeMillis();
        //System.out.println("排序前: " + start);

        heapSort(arr);

        long end = System.currentTimeMillis();
        //System.out.println("排序后: " + end);
        System.out.println("耗时: " + (end - start));
        //System.out.println("排序后: " + Arrays.toString(arr));
    }

    /**
     * 堆排序的方法
     * @param arr 需要排序的数组
     */
    public static void heapSort(int arr[]){
        System.out.println("堆排序!!");

        /*//分步完成
        adjustHeap(arr, 1, arr.length);
        //[4, 9, 8, 5, 6]
        System.out.println("第一次: " + Arrays.toString(arr));

        adjustHeap(arr, 0, arr.length);
        //[9, 6, 8, 5, 4]
        System.out.println("第二次: " + Arrays.toString(arr));*/

        int temp = 0;
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /*
        将对顶元素与末尾元素交换，将最大元素"沉"到数组的末端
        重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
         */
        for (int i = arr.length - 1; i > 0; i--) {
            //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
        //System.out.println("数组: " + Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树），调整为一个大顶堆
     * 完成将以i对应的非叶子结点的树调整成一个大顶堆
     * 举例：int arr[] = {4,6,8,5,9}; => i=1 => adjustHeap => 得到 {4,9,8,5,6}
     * 再次调用adjustHeap传入的是i=0 => 得到{9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int arr[],int i,int length){
        //先取出当前元素的值，保存在临时变量
        int temp = arr[i];
        // j = i * 2 + 1 j是i结点的左子结点
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //说明左子节点的值小于右子结点的值
            if (j +1 < length && arr[j] < arr[j+1]){
                j++;
            }
            //如果子结点的值大于父结点的值
            if (arr[j] > temp){
                //把较大的值赋给当前结点
                arr[i] = arr[j];
                //i指向j，继续循环比较
                i = j;
            }else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶（局部）
        //将temp值放到调整后的位置
        arr[i] = temp;
    }
}
