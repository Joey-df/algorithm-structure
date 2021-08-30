package string;

/**
 * 389. 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * <p>
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * <p>
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 * <p>
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 */
public class Problem_0389_FindTheDifference {

    //代码核心：
    //使用一个int变量，累加所有字符的字面值即可
    public static char findTheDifference(String s, String t) {
        if (t.length() - s.length() != 1) {
            throw new RuntimeException("输入参数不合法");
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int code1 = 0;
        int code2 = 0;
        for (char c : str1) code1 += c;
        for (char c : str2) code2 += c;
        return (char) (code2 - code1);
    }

    public static void main(String[] args) {
        String s = "y";
        String t = "ys";
        System.out.println(findTheDifference(s, t));
    }
}
