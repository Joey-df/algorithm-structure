package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [2,3]
 */
public class Problem_0442_FindAllDuplicatesInAnArray {

    //平凡解
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums==null || nums.length==0) {
            return ans;
        }
        int N = nums.length;
        int[] map = new int[N+1]; //0弃而不用
        for (int num : nums) map[num]++;
        for (int i = 1; i <= N ; i++) {
            if (map[i]==2) ans.add(i);
        }
        return ans;
    }

    //最优解
    //空间O(1) 时间O(N)
    public static List<Integer> findDuplicates2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums==null || nums.length==0) {
            return ans;
        }
        for (int num : nums) { // 如果是3 就是 找nums[2]
            int n = Math.abs(num); //因为可能已经被改为负数了
            int index = n - 1;
            if(((nums[index] >> 31) & 1) == 1) ans.add(n);
            nums[index] = ~nums[index]+1;
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(findDuplicates2(new int[]{4,3,2,7,8,2,3,1}));
    }
}
