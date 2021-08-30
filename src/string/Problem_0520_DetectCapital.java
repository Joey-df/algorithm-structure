package string;

/**
 * 520. 检测大写字母
 * 给定一个单词，你需要判断单词的大写使用是否正确。
 * <p>
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 * <p>
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "USA"
 * 输出: True
 * 示例 2:
 * <p>
 * 输入: "FlaG"
 * 输出: False
 * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
 */
public class Problem_0520_DetectCapital {

    public static boolean detectCapitalUse(String word) {
        char[] str = word.toCharArray();
        int count = 0;
        int n = str.length;
        boolean startWith = false;
        for (int i = 0; i < n; i++) {
            if (isCapital(str[i])) {
                if (i == 0) startWith = true;
                count++;
            }
        }
        return count == n || (startWith && count == 1) || count == 0;
    }

    public static boolean isCapital(char c) {
        return c >= 'A' && c <= 'Z';
    }

}
