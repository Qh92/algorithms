package com.qinh.zcy.class02.mergesort;

import java.util.Arrays;

/**
 * 小和问题：
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和
 * 求一个数组的小和
 * 例子：[1,3,4,2,5] 1左边比1小的数，没有；3左边比3小的数，1；4左边比4小的数，1、3；2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；所以小和为1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-02 16:50
 */
public class SmallSum {

    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        System.out.println(process(arr, 0, arr.length - 1));
        System.out.println(Arrays.toString(arr));
    }

    /**
    可以将小和问题换个方向思考：当前这个数，找到右边比它大的数，如果能找到，当前这个数就是一个小和
    上述问题可以这样：
    1在右边能找到4个比它大的数，4 * 1
    3在右边能找到2个比它大的数，2 * 3
    4在右边能找到1个比它大的数，1 * 4
    2在右边能找到1个比它大的数，1 * 2
    结果 = 4 + 6 + 4 + 2 = 16
    如果这样思考，上述问题可以采用归并排序解决
     */
    public static int process(int[] arr,int l,int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return  process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int i = 0;
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int res = 0;

        while (p1 <= mid && p2 <= r) {
            // 小和增加的数量
            //res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            // 逆序对数量
            res += arr[p2] < arr[p1] ? mid - p1 + 1 : 0;
            //help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            // 逆序对，如果左边的数和右边的数相等的话，拷贝左边的数
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        if (help.length >= 0) {
            System.arraycopy(help, 0, arr, l, help.length);
        }
        return res;
    }
}
