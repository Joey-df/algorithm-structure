package string;

/**
 * 151. 翻转字符串里的单词
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * 说明：
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 *
 * 示例 1：
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是翻转后的字符不能包括。
 * 示例 3：
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将翻转后单词间的空格减少到只含一个。
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 */
//https://leetcode-cn.com/problems/reverse-words-in-a-string/
public class Problem_0151_ReverseWordsInAString {

    //方法1：直接使用split
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        //trim()会去除s开头和结尾的空白符
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            if (i != 0) sb.append(" "); //除了最后一个单词，每个单词之间用空格连接
        }
        return sb.toString();
    }

    //方法2：字符数组 + 双指针
    public static String reverseWords2(String s) {
        if (s == null || s.length() == 0) return s;
        s = s.replaceAll("\\s+", " "); //把所有空白符替换尾空格
        char[] str = s.toCharArray();
        int n = str.length;
        reverse(str, 0, n - 1);
        int l = 0;
        int r = 0;
        while (r < n) {
            while (r < n && str[r] != ' ') r++; //r到空格停，说明一个单词结尾了
            reverse(str, l, r - 1);
            l = r + 1;
            r = l;
        }
        //分别处理首尾的空格
        String ans = String.valueOf(str);
        if (ans.charAt(0) == ' ') {
            ans = ans.substring(1);
        }
        if (ans.charAt(ans.length() - 1) == ' ') {
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

    //str[l,r]范围上反转
    public static void reverse(char[] str, int l, int r) {
        while (l < r) {
            char t = str[l];
            str[l++] = str[r];
            str[r--] = t;
        }
    }
}
