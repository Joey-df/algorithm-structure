package backtrack;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 打印字符串的全部子序列
 * 1、字符串不包括重复字符
 * 2、字符串包括重复字符
 */
public class AllSubSequences {

    public static void main(String[] args) {
        String str = "aa";
        char[] arr = str.toCharArray();
//        List<String> ans  =new ArrayList<>();
//        subSequenceWithNoRepeate(arr, 0, "",ans);
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.println(ans.get(i));
//        }
        Set<String> ans = new HashSet<>();
        subSequenceWithRepeate(arr, 0, "", ans);
        for (String s : ans) {
            System.out.println(s);
        }
    }

    /**
     * @param str   字符串对应的字符数组
     * @param index str[0...index-1]已经决定好了。str[index...]自由做选择
     * @param path  str[0...index-1]形成的字符序列是啥
     * @param ans   当index到达结束位置时，收集答案
     * @return
     */
    private static void subSequenceWithNoRepeate(char[] str, int index, String path, List<String> ans) {
        if (str == null || str.length == 0) {
            return;
        }
        if (index == str.length) { //表示str[0...str.length-1]已经做好决定了，收集答案
            ans.add(path);
            return;
        }
        //index后面还有字符，分两种情况：1、要当前字符，2、不要当前字符
        subSequenceWithNoRepeate(str, index + 1, path, ans);
        subSequenceWithNoRepeate(str, index + 1, path + String.valueOf(str[index]), ans);
    }

    /**
     * @param str   字符串对应的字符数组
     * @param index str[0...index-1]已经决定好了。str[index...]自由做选择
     * @param path  str[0...index-1]形成的字符序列是啥
     * @param ans   当index到达结束位置时，收集答案。使用set去重
     * @return
     */
    private static void subSequenceWithRepeate(char[] str, int index, String path, Set<String> ans) {
        if (str == null || str.length == 0) {
            return;
        }
        if (index == str.length) { //表示str[0...str.length-1]已经做好决定了，收集答案
            ans.add(path);
            return;
        }
        //index后面还有字符，分两种情况：1、要当前字符，2、不要当前字符
        subSequenceWithRepeate(str, index + 1, path, ans);
        subSequenceWithRepeate(str, index + 1, path + String.valueOf(str[index]), ans);
    }

}
