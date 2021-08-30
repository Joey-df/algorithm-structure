package string;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * <p>
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class Problem_0557_ReverseWordsInAStringIII {

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            //如果i位置是空格 直接i++
            if (str[i] != ' ') { // when i is a non-space
                int start = i;
                int end = i;
                while (end + 1 < str.length && str[end + 1] != ' ') {
                    end++; // move end to the end of the word
                }
                reverse(str, start, end);
                i = end; //i跳到单词的结尾
            }
        }
        return String.valueOf(str);
    }

    public static void reverse(char[] str, int l, int r) {
        if (str == null || str.length == 0 || l >= r) return;
        while (l < r) {
            char t = str[l];
            str[l++] = str[r];
            str[r--] = t;
        }
    }


    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode    contest"));
    }
}
