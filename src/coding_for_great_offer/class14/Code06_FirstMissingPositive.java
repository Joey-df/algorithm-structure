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


    //最优解
    public static int firstMissingPositive2(int[] arr) {
        // l是盯着的位置
        // 0 ~ L-1有效区
        int L = 0;
        int R = arr.length;
        while (L != R) {
            if (arr[L] == L + 1) {
                L++;
            } else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // 垃圾的情况
                swap(arr, L, --R);
            } else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
