package leetcode_top_interview_questions;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class Problem_0017_LetterCombinationsOfAPhoneNumber {

    private static Map<Character, String> initMap() {
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


    //递归含义
    //nums：输入的数字字符串 对应的字符数组
    //index：当前来到的位置（index-1及其往前都决定好了，不用操心了）
    //path：[0...index-1]沿途形成的路径
    //ans：收集答案
    private static void fun(char[] nums, int index, LinkedList<Character> path, List<String> ans, Map<Character, String> map) {
        if (index == nums.length) {
            //表示[0,N-1]已经决定好了
            StringBuilder sb = new StringBuilder();
            for(char c : path) {
                sb.append(c);
            }
            ans.add(sb.toString());
        } else {
            //index还有数字需要搞定
            char cur = nums[index];
            String s = map.get(cur);
            char[] str = s.toCharArray();
            for (char c : str) {//枚举index位置的字符选择
                path.addLast(c);
                fun(nums, index + 1, path, ans, map);
                path.pollLast();
            }
        }
    }


    public static List<String> letterCombinations(String digits) {
        Map<Character, String> map = initMap();
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        char[] nums = digits.toCharArray();
        LinkedList<Character> path = new LinkedList<>();
        fun(nums, 0, path, ans, map);
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(letterCombinations("23222"));
    }
}
