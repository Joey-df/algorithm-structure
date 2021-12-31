package greedy;

import java.util.Arrays;

/**
 * 561. 数组拆分I
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对,
 * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 * 返回该 最大总和 。
 *
 * 示例 1：
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 *
 * 示例 2：
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 *
 * 提示：
 * 1 <= n <= 10^4
 * nums.length == 2 * n
 * -10^4 <= nums[i] <= 10^4
 */
public class Problem_0561_ArrayPartitionI {

    //O(n)
    public int arrayPairSum(int[] nums) {
        //bucket sort
        int maxNumberAllowed = 10000; //given in question -10^4 <= nums[i] <= 10^4
        int[] bucket = new int[(maxNumberAllowed << 1) | 1]; //we will create double size bucket since we have to handle negative numbers also

        //fill bucket
        for(int num: nums){
            bucket[10000 + num]++;
        }

        //iterate bucket
        boolean shouldPick = true; // 作用：如果排序的话，取偶数位(0，2，4...)的数累加
        int sum = 0;
        for(int i=0;i<=20000;i++){
            //pick one and skip one in sorted order array
            while(bucket[i] > 0){
                if(shouldPick){
                    sum+= (i - 10000);
                }
                shouldPick = !shouldPick; //flip the sign
                bucket[i]--;
            }
        }

        return sum;
    }

    //O(n*logn)
    public int arrayPairSum2(int[] nums) {
        Arrays.sort(nums);
        int sum=0;
        for (int i = 0; i < nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }

}
