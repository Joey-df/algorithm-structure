package array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 645. 错误的集合
 * 集合 s 包含从 1 到 n 的整数。
 * 不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
 * 导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 * 示例 2：
 * 输入：nums = [1,1]
 * 输出：[1,2]
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 */
public class Problem_0645_SetMismatch {
    //前提 数组nums包含从 1 到 n 的整数。
    public int[] findErrorNums(int[] nums) {
        int N = nums.length;
        int[] count = new int[N+1]; //0弃而不用
        for (int num: nums) {
            count[num]++;
        }
        int p1=-1,p2=-1;
        for (int i = 1; i < count.length; i++) {
            p1 = (count[i]==2) ? i : p1;
            p2 = (count[i]==0) ? i : p2;
        }
        return new int[]{p1,p2};
    }

    public int[] findErrorNums2(int[] nums) {
        //思路：
        //第一步;找出重复元素（通过hashMap）
        HashMap<Integer, Integer> map = new HashMap<>();
        int repeatNum = -1;
        for(int num: nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num)+1);
                repeatNum = num;
            }
        }
        int lost = 0; //0^N==N
        //第二步：通过异或找出缺失的数
        for(int key: map.keySet()) {
            lost ^= key; //1^2^4
        }
        for (int i=1; i<=nums.length; i++) { //1,2,3,4
            lost ^= i;
        }
        return new int[]{repeatNum, lost};
    }


    public int[] findErrorNums3(int[] nums) {
        //方法二：通过HashSet找出重复元素，利用等差数列求和公式抓到缺失值
        int repeatNum = -1;
        int setSum = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                repeatNum = num;
                continue;
            }
            set.add(num);
            setSum += num;
        }
        int lost = (nums.length*(nums.length+1))/2 - setSum;
        return new int[]{repeatNum, lost};
    }

}
