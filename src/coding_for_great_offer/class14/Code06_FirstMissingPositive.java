package coding_for_great_offer.class14;

// leetcode41. First Missing Positive
// 测试链接：https://leetcode.com/problems/first-missing-positive/
// leetcode268. Missing Number
// https://leetcode.com/problems/missing-number/
import java.util.HashSet;
import java.util.Set;

/**
 * 给定数组nums，可正可负可0，返回缺失的最小正数
 */
public class Code06_FirstMissingPositive {

    public static int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (i > 0) {
                set.add(i);
            }
        }
        int i = 0;
        for (; i < set.size(); i++) {
            if (!set.contains(i + 1)) {
                break;
            }
        }
        return i + 1;
    }

}
