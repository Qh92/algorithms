package com.qinh.zcy.class01;

/**
 * 1、给定一个整型数组，
 * 如果其中只有一种数出现奇数次，其它数都出现偶数次，求出现奇数次的数
 * 如果其中有两种数出现奇数次，其它数都出现偶数次，求出现奇数次的两个数
 *
 * 注：要求时间复杂度O(n)，空间复杂度O(1)
 *
 * @author Qh
 * @version 1.0
 * @date 2021-12-26 16:52
 */
public class DigitwiseArray {
    public static void main(String[] args) {
        //如果其中只有一种数出现奇数次，其它数都出现偶数次，求出现奇数次的数
        int[] arr = {1,2,3,2,3,4,5,1,4};
        int value = 0;
        for (int val : arr) {
            value ^= val;
        }
        System.out.println(value);


        //如果其中有两种数出现奇数次，其它数都出现偶数次，求出现奇数次的两个数
        int[] arr2 = {1,2,3,2,3,4,5,1,4,6};
        //最终异或出来的结果为 两个奇数次的数异或出来的结果
        int eor1 = 0;
        for (int val : arr2) {
            eor1 ^= val;
        }

        //更优解法
        // eor1 != 0,eor1必然有一个位置上是1
        // 提取出最右的1
        int rightOne = eor1 & (~eor1 + 1);

        int onlyOne = 0;
        for (int val : arr2) {
            if ((val & rightOne) == 0) {
                onlyOne ^= val;
            }
        }
        System.out.println(onlyOne + " " + (eor1 ^ onlyOne));


        /*String binaryString = Integer.toBinaryString(eor1);
        System.out.println(binaryString);

        //记录二进制位数为1的位数
        int index = -1;
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '1') {
                index = i;
                break;
            }
        }
        System.out.println("index : " + index);
        if (index == -1) {
            throw new RuntimeException("数据有误");
        }
        //index = binaryString.length() - index;

        // 其中一个奇数次数的数
        int eor2 = 0;
        for (int val : arr2) {
            String s = Integer.toBinaryString(val);
            if (s.length() > index && s.charAt(index) == '1') {
                eor2 ^= val;
            }
        }

        // 另外一个奇数次数的数
        int eor3 = eor1 ^ eor2;
        System.out.println("eor2 : " + eor2);
        System.out.println("eor3 : " + eor3);*/
        System.out.println("-------test");
        System.out.println(Integer.toBinaryString(0));

    }
}
