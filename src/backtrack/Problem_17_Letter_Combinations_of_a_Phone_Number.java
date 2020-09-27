package backtrack;

import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * 
 * Example:
 * 
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * 
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class Problem_17_Letter_Combinations_of_a_Phone_Number {

    //获取每个2～9 每个数字对应的字符集合
    private static Map<Character, String> help() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }

    //递归含义：
    //digits[index...N-1]可能的组合 返回
    //[0...index-1]已经搞定了，不用再操心了
    //path: [0...index-1]已经形成的路径
    public static void process(String digits, int index, LinkedList<Character> path, List<String> ans, Map<Character, String> map) {
        char[] str = digits.toCharArray();
        int N = str.length;
        if (index == N) {
            //表示已经到达末尾
            //[0...N-1]已经搞定了，形成的path就是一种答案，此时应该收集答案
            StringBuilder sb = new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            ans.add(sb.toString());
            return;
        }
        //index后面还有字符
        char num = str[index];
        String charSet = map.get(num);
        char[] strSet = charSet.toCharArray();
        for (int i = 0; i < charSet.length(); i++) {
            path.addLast(strSet[i]);
            process(digits, index + 1, path, ans, map);
            path.pollLast();
        }
    }

    //潜台词：digits中的数组只包含2～9
    public static List<String> ways(String digits) {
        List<String> ans = new ArrayList<>();
        if ("".equals(digits) || digits == null || digits.length() == 0) {
            return ans;
        }
        LinkedList<Character> path = new LinkedList<>();
        Map<Character, String> map = help();
        process(digits, 0, path, ans, map);
        return ans;
    }

    public static void main(String[] args) {
        String s = "29";
        System.out.println(ways(s));
    }
}
