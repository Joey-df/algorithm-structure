package string;

import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 * <p>
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 * <p>
 * Input: strs = ["a"]
 * Output: [["a"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lower-case English letters.
 */
public class Problem_49_Group_Anagrams {

    private static class Node {
        String origin;
        String sorted;

        public Node(String origin, String sorted) {
            this.origin = origin;
            this.sorted = sorted;
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return ans;
        }
        Node[] nodes = new Node[strs.length];
        for (int i = 0; i < strs.length; i++) {
            char[] cur = strs[i].toCharArray();
            Arrays.sort(cur);
            nodes[i] = new Node(strs[i], String.valueOf(cur));
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < nodes.length; i++) {
            String key = nodes[i].sorted;
            if (map.containsKey(key)) {
                map.get(key).add(nodes[i].origin);
            } else {
                List<String> sub = new ArrayList<>();
                sub.add(nodes[i].origin);
                map.put(key, sub);
            }
        }
        for (List<String> list : map.values()) {
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{}));
    }
}
