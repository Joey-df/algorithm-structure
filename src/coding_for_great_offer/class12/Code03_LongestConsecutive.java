package coding_for_great_offer.class12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
public class Code03_LongestConsecutive {

    public static int longest(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        int best = Integer.MIN_VALUE;
        for (int x : arr) {
            if (!set.contains(x - 1)) {//x可以做开头
                int y = x;
                while (set.contains(y)) {
                    y++;
                }
                best = Math.max(best, y - x);
            }
        }
        return best;
    }

    //方法二
    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int all = preLen + posLen + 1;
                map.put(num - preLen, all);
                map.put(num + posLen, all);
                len = Math.max(len, all);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(longest(new int[]{100,200,30,2,1,4,5}));
    }
}