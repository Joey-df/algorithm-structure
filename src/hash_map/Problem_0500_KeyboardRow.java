package hash_map;

//500. 键盘行
//https://leetcode-cn.com/problems/keyboard-row/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 * 美式键盘 中：
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 * <p>
 * 示例 1：
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 */
public class Problem_0500_KeyboardRow {

    public static String[] findWords(String[] words) {
        Set<Character> row1 = strToCharSet("qwertyuiop");
        Set<Character> row2 = strToCharSet("asdfghjkl");
        Set<Character> row3 = strToCharSet("zxcvbnm");

        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (isValid(row1, word) || isValid(row2, word) || isValid(row3, word)) {
                res.add(word);
            }
        }
        //System.out.println(res);
        return res.toArray(new String[0]); //List转数组
    }

    public static boolean isValid(Set<Character> set, String word) {
        for (char c : word.toCharArray()) {
            if (!set.contains(Character.toLowerCase(c))) return false;
        }
        return true;
    }

    public static Set<Character> strToCharSet(String str) {
        Set<Character> set = new HashSet<>();
        for (char c : str.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static void main(String[] args) {
        String[] words = {"Hello","Alaska","Dad","Peace"};
        findWords(words);
    }

}
