package slide_window;

/**
 * 给定字符串str1和str2，求str1的子串中含有str2所有字符的最小子串长度
 * 【举例】
 * str1="abcde"，str2="ac"
 * 因为"abc"包含 str2 所有的字符，并且在满足这一条件的str1的所有子串中，"abc"是 最短的，返回3。
 * str1="12345"，str2="344" 最小包含子串不存在，返回0。
 * <p>
 * 第39节、经典面试题(二十三)第一题
 * 1、和顺序没有关系的
 * 2、这道题是求最小长度，leetcode76题是求最小长度的子串
 */
public class Problem_MinWindowLength {

    public static int process(String str, String target) {
        if (str.length() < target.length()) {
            return -1;
        }
        char[] str1 = str.toCharArray();
        char[] str2 = target.toCharArray();

        int[] map = new int[256];//欠账表
        for (char c : str2) {
            map[c]++;
        }
        int all = str2.length;
        // 初始窗口为[L,R) 即[0,0) 表示窗口内一个数也没有
        int L = 0;
        int R = 0; //R表示即将进入窗口的位置
        int ans = Integer.MAX_VALUE;
        while (R < str1.length) {
            // R位置的字符进窗口
            if (--map[str1[R]] >= 0) {//先--再判断
                all--;
            }
            if (all == 0) {//表示还完了
                while (map[str1[L]] < 0) { //map[str1[L]] == 0时跳出while
                    map[str1[L++]]++;
                }
                ans = Math.min(ans, R - L + 1);//抓答案
                //尝试下一个位置
                map[str1[L]]++;
                L++;
                all++;
            }
            R++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    public static void main(String[] args) {
        String str1 = "abcde", str2 = "ac";
        System.out.println(process(str1, str2));
    }
}
