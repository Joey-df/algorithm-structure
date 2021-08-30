package string;

//151. 翻转字符串里的单词
//https://leetcode-cn.com/problems/reverse-words-in-a-string/
public class Problem_0151_ReverseWordsInAString {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        String[] arr = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
            if (i != 0) sb.append(" ");
        }
        return sb.toString();
    }

}
