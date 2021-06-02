package coding_for_great_offer.class12;

// 本题测试链接 : https://leetcode.com/problems/permutation-in-string/
public class Code01_ContainAllCharExactly {

    public static int containExactly3(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return -1;
        }
        char[] str2 = s2.toCharArray();
        int M = str2.length;
        int[] count = new int[256];
        for (int i = 0; i < M; i++) {
            count[str2[i]]++;
        }
        int all = M;
        char[] str1 = s1.toCharArray();
        int R = 0;
        // 0~M-1
        for (; R < M; R++) { // 最早的M个字符，让其窗口初步形成
            if (count[str1[R]]-- > 0) {
                all--;
            }
        }
        System.out.println(all);
        // 窗口初步形成了，并没有判断有效无效，决定下一个位置一上来判断
        // 接下来的过程，窗口右进一个，左吐一个
        for (; R < str1.length; R++) {
            if (all == 0) { // R-1
                return R - M;
            }
            if (count[str1[R]]-- > 0) {
                all--;
            }
            if (count[str1[R - M]]++ >= 0) {
                all++;
            }
        }
        System.out.println(all);
        return all == 0 ? R - M : -1;
    }

    public static void main(String[] args) {
        System.out.println(containExactly3("xyzabzababc","abacb"));
    }
}