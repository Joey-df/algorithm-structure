package train_camp_03.class07;

/**
 * 给定一个全是小写字母的字符串str，删除多余字符，使得每种字符只保留一个，并让 最终结果字符串的字典序最小
 * 【举例】
 * str = "acbc"，删掉第一个'c'，得到"abc"，是所有结果字符串中字典序最小的。
 * str = "dbcacbca"，删掉第一个'b'、第一个'c'、第二个'c'、第二个'a'，得到"dabc"， 是所有结 果字符串中字典序最小的。
 * <p>
 * http://chenxii.cn/2020/02/22/D-DataStructureAndAlgorithm/E-ZuoInterview/71-removeDuplicateLetters/
 * 贪心
 */
public class Code05_RemoveDuplicateLettersLessLexi {

    public static String process(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        int minASCIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(minASCIndex)) {
                minASCIndex = i;
            }

            if (--map[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        return s.charAt(minASCIndex) +
                process(s.substring(minASCIndex + 1).replaceAll(s.charAt(minASCIndex) + "", ""));
    }

    public static void main(String[] args) {
        System.out.println(process("dbcacbca"));
    }
}
