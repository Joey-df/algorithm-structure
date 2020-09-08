package recursive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串的全排列
 * 包括无重复字符 有重复字符的实现
 * 本质是深度优先搜索
 */
public class Permutations {

    public static void main(String[] args) {
        String s = "abc";
        List<String> list = permutationWithNoRepeate(s);
//        List<String> list = permutationWithRepeate(s);
        System.out.println("list.size() = " + list.size());
        for (String str : list) {
            System.out.println(str);
        }
    }

    private static List<String> permutationWithNoRepeate(String str) {
        char[] s = str.toCharArray();
        List<String> ans = new ArrayList<>();
        noRepeate(s, 0, ans);
        return ans;
    }

    /**
     * str[0...index-1]之前已经做好决定了
     * str[index...]上的字符都有机会来到i位置
     *
     * @param str   里面没有重复字符
     * @param index
     * @param ans   index来到终止位置，str当前的样子就是一种结果，收集到ans中
     */
    private static void noRepeate(char[] str, int index, List<String> ans) {
        if (index == str.length) { //str[0...str.length-1]之前已经做好决定了
            ans.add(String.valueOf(str));
        }
        //index没有到终止位置
        for (int i = index; i < str.length; i++) {
            swap(str, index, i);
            noRepeate(str, index + 1, ans);
            swap(str, index, i);//恢复现场
        }
    }

    private static List<String> permutationWithRepeate(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        withRepeate(chs, 0, res);
        return res;
    }

    /**
     * str[0...index-1]之前已经做好决定了
     * str[index...]上的字符都有机会来到i位置
     *
     * @param str   里面有重复字符
     * @param index
     * @param ans   index来到终止位置，str当前的样子就是一种结果，收集到ans中
     */
    private static void withRepeate(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        }
        //index没有到终止位置
        Set<Character> set = new HashSet<>(); //作用与index位置，每个位置一份，不共享
//        boolean[] visit = new boolean[26]; // visit[0 1 .. 25]
        for (int i = index; i < str.length; i++) {
            if (!set.contains(str[i])) {
//            if (!visit[str[i] - 'a']) {
                set.add(str[i]);
//                visit[str[i] - 'a'] = true;
                swap(str, index, i);
                withRepeate(str, index + 1, ans);
                swap(str, index, i);//恢复现场
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
