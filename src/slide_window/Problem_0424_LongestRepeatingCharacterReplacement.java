package slide_window;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过 10^4。
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * Constraints:
 * 1 <= s.length <= 10^5
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 */
public class Problem_0424_LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {
        char[] str = s.toCharArray();
        int[] freq = new int[26];
        int mostFreqLetter = 0;
        int l = 0;
        int max = 0;
        // 窗口范围为 [l,r]
        for (int r = 0; r < s.length(); r++) {
            freq[str[r] - 'A']++;
            mostFreqLetter = Math.max(mostFreqLetter, freq[str[r] - 'A']);

            int lettersToChange = (r - l + 1) - mostFreqLetter;
            if (lettersToChange <= k) {
                max = Math.max(max, r - l + 1);
            } else { //r来到了第一个违规的字符位置
                freq[str[l] - 'A']--;
                l++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k));
    }

}
