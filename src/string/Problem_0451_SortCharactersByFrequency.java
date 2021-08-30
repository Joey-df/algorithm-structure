package string;

import java.util.*;

/**
 * 451. 根据字符出现频率排序
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * <p>
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * ""
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * <p>
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 */
public class Problem_0451_SortCharactersByFrequency {


    public static String frequencySort(String s) {
        char[] str = s.toCharArray();
        int[] count = new int[256]; //每一个下标代表的是字符的ascII码
        for (char c : str) count[c]++;
        //key：出现的次数
        //value：出现这个次数对应的字符有哪些
        TreeMap<Integer, List<Character>> map = new TreeMap<>((o1, o2) -> o2 - o1); //按出现次数降序
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                if (!map.containsKey(count[i])) {
                    map.put(count[i], new ArrayList<>());
                }
                map.get(count[i]).add((char) i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Character>> e : map.entrySet()) {
            int C = e.getKey();//出现的次数C
            List<Character> list = e.getValue();//出现C次的字符有哪些
            for (char c : list) { //每种字符添加C次
                for (int j = 0; j < C; j++) sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("treeaaa"));
    }
}
