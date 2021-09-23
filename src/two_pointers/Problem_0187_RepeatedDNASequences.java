package two_pointers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 187. 重复的DNA序列
 * 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * 编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
 *
 * 示例 1：
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 *
 * 示例 2：
 * 输入：s = "AAAAAAAAAAAAA"
 * 输出：["AAAAAAAAAA"]
 *
 * 提示：
 * 0 <= s.length <= 10^5
 * s[i] 为 'A'、'C'、'G' 或 'T'
 */
public class Problem_0187_RepeatedDNASequences {

    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> ans = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (set.contains(ten)) {
                ans.add(ten);
            }
            set.add(ten);
        }
        return new ArrayList<>(ans);
    }

    //from StefanPochmann
    public static List<String> findRepeatedDnaSequences2(String s) {
        Set<String> set = new HashSet<>();
        Set<String> ans = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!set.add(ten)) {  //add：之前没加过，返回true；之前已添加过，返回false
                ans.add(ten);
            }
        }
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s));
    }

}
