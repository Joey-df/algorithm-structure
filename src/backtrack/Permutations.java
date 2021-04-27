package backtrack;

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
        List<String> list = process1(s.toCharArray());
        System.out.println("list.size() = " + list.size());
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("====================");

        s = "aba";
        List<String> list2 = process2(s.toCharArray());
        System.out.println("list2.size() = " + list2.size());
        for (String str : list2) {
            System.out.println(str);
        }
    }

    /**
     * 求给定字符数组arr的所有全排列
     * @param arr 原始字符数组，arr中没有重复字符
     * @return 所有全排列组成的list
     */
    public static List<String> process1(char[] arr) {
        List<String> ans = new ArrayList<>();
        if (arr==null||arr.length==0) return ans;
        f(arr, 0, ans);
        return ans;
    }

    /**
     *
     * @param arr 原始字符数组
     * @param index 当前来到i位置，[0,index-1]已经做好决定了
     * @param ans 收集答案
     */
    private static void f(char[] arr, int index, List<String> ans) {
        //表示[0,arr.length-1]已经做好决定了,此时arr就是一种答案
        if (index==arr.length) {
            ans.add(String.valueOf(arr));
            return;
        }
        //index后面的字符都有机会来到i位置
        for (int i = index; i < arr.length; i++) {
            swap(arr, i,index);
            f(arr, index+1, ans);
            swap(arr, i, index);//恢复现场
        }
    }

    /**
     * 求给定字符数组arr的所有全排列
     * @param arr 原始字符数组，arr中存在重复字符
     * @return 所有全排列组成的list
     */
    public static List<String> process2(char[] arr) {
        List<String> ans = new ArrayList<>();
        if (arr==null||arr.length==0) return ans;
        g(arr, 0, ans);
        return ans;
    }

    /**
     *
     * @param arr 原始字符数组 存在重复字符
     * @param index 当前来到i位置，[0,index-1]已经做好决定了
     * @param ans 收集答案
     */
    public static void g(char[] arr, int index, List<String> ans) {
        if (index==arr.length) {
            ans.add(String.valueOf(arr));
            return;
        }
        boolean[] visited = new boolean[256];//专为index位置使用：index位置剪枝
        for (int i = index; i < arr.length; i++) {
            if (!visited[arr[i]]) {
                visited[arr[i]] = true;
                swap(arr, i, index);
                g(arr, index+1, ans);
                swap(arr, i, index); //恢复现场
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
