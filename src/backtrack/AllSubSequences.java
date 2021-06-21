package backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印字符串的全部子序列
 * 1、字符串不包括重复字符
 * 2、字符串包括重复字符
 * <p>
 * 经典的从左往右的尝试
 */
public class AllSubSequences {

    public static void main(String[] args) {
        String str = "123";
        char[] arr = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process1(arr, 0, "", ans);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
        System.out.println("===========");
        str = "aaa";
        Set<String> ans1 = new HashSet<>();
        process2(str.toCharArray(), 0, "", ans1);
        for (String s : ans1) {
            System.out.println(s);
        }
    }

    /**
     * arr中没有重复字符，求arr的所有子序列
     *
     * @param arr  原始字符数组，固定参数
     * @param i    [0,i-1]已经搞定了不用操心了，当前来到i位置做决定
     * @param path [0,i-1]做决定形成的字符串存在path里
     * @param ans  专为收集答案
     */
    public static void process1(char[] arr, int i, String path, List<String> ans) {
        if (arr == null || arr.length == 0) return;
        //base case 收集答案的时机
        if (i == arr.length) {//表示[0,arr.length-1]已经做好决定了，此时path就是一种答案
            ans.add(path);
            return;
        }
        //i<arr.length
        process1(arr, i + 1, path, ans);
        process1(arr, i + 1, path + arr[i], ans);
    }

    /**
     * arr中包含重复字符，求arr的所有子序列
     *
     * @param arr  原始字符数组，固定参数
     * @param i    [0,i-1]已经做好决定了，不用操心了。当前来到i位置做决定
     * @param path [0,i-1]做的决定形成的字符串，存在path中
     * @param ans  收集答案
     *             这里不需要清除现场，是因为path每次都是新的，
     *             String在后台每次都会弄个新的
     */
    public static void process2(char[] arr, int i, String path, Set<String> ans) {
        if (arr == null || arr.length == 0) return;
        //表示[0,arr.length-1]已经做好决定了，此时path正好是一种答案，需要收集
        if (i == arr.length) {
            ans.add(path);
            return;
        }
        process2(arr, i + 1, path, ans);
        process2(arr, i + 1, path + arr[i], ans);
    }

}
