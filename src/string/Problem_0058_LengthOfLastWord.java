package string;

/**
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class Problem_0058_LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s==null || s.length()==0) return 0;
        String[] arr = s.split("\\s+");
        return arr[arr.length-1].trim().length();
    }

}