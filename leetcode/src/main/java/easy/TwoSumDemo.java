package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-10 21:59
 */
public class TwoSumDemo {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        //nums = new int[]{3, 2, 4};
        //nums = new int[]{3, 3};
        int target = 9;
        //target = 6;
        int[] index = getSumIndex(nums, target);
        System.out.println(Arrays.toString(index));
    }

    private static int[] getSumIndex(int[] arr,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])){
                return new int[]{map.get(target - arr[i]),i};
            }
            map.put(arr[i],i);
        }
        return null;
    }
}
