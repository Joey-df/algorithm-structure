package bit_manipulation;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * 提示：
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class Problem_0318_MaximumProductOfWordLengths {

    //This is an awesome solution!
    //The code could be a little bit better.
    public static int maxProduct(String[] words) {
        int len = words.length;
        //set中使用每一个int类型的整数表示每个单词字母出现的信息
        int[] set = new int[len];
        for (int i = 0; i < len; i++) {
            for (char a : words[i].toCharArray()) {
                set[i] |= (1 << a - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((set[i] & set[j]) == 0) { //表示两个单词没有重复字母
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}
