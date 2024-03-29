package bit_manipulation;

import java.util.HashSet;
import java.util.Set;

/**
 * 268. 丢失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 * 进阶：
 * 你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 */
//leetcode41同一类题
public class Problem_0268_MissingNumber {

    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int n = nums.length;
        int i=0;
        for (; i <= n; i++) {
            if (!set.contains(i))
                break;
        }
        return i;
    }

    public int missingNumber2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        int ans = 0;
        while (set.contains(ans)) {
            ans++;
        }
        return ans;
    }

}
