package train_camp_03.class05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
 * 比如 s1 = "abcde"，s2 = "axbc"
 * 返回1。s2删掉'x'就是s1的子串了。
 */
public class Code01_DeleteMinCost {

    //方法一：
    //使用与s1.length()>s2.length() 并且 s2.length()较小
    //求出s2所有的子序列seq list，按长度从大到小排好序
    //然后遍历sorted seq list，判断每一个子序列是否为s1的子串，抓答案
    public static int ways1(String s1, String s2) {
        if (s2.length() == 0) {
            return 0;
        }
        List<String> seqList = new ArrayList<>();
        getSeqList(s2.toCharArray(), seqList, new StringBuilder(), 0);
        seqList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        for (int i = 0; i < seqList.size(); i++) {
            String sub = seqList.get(i);
            if (s1.contains(sub)) { // indexOf底层和KMP算法代价几乎一样，也可以用KMP代替
                return s2.length() - sub.length();
            }
        }
        return -1;
    }

    //递归含义：
    //str[index...N-1]的所有子序列 收集到ans中
    private static void getSeqList(char[] str, List<String> ans, StringBuilder path, int index) {
        if (str == null || str.length == 0) return;
        if (index == str.length) {
            ans.add(path.toString());
            return;
        }
        //index后面还有字符
        getSeqList(str, ans, path.append(str[index]), index + 1);//要index位置字符
        getSeqList(str, ans, path, index + 1); //不要index位置字符
    }

    //方法二：
    //生成s1所有的子串(O(N^2)),s2与每个子串求最小编辑距离(假设编辑距离只有删除动作且删除一个字符的代价为1)
    //如果s1长度较小，s2长度较大，这个方法合适一些
    public static int ways2(String s1, String s2) {
        int ans = Integer.MAX_VALUE;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str1.length; j++) {
                String substr = s1.substring(i, j + 1); //i～j的子串
                ans = Math.min(ans, distance(str2, substr.toCharArray()));
            }
        }
        return ans == Integer.MAX_VALUE ? s2.length() : ans;
    }

    // 求str2到s1sub的编辑距离
    // 假设编辑距离只有删除动作且删除一个字符的代价为1
    public static int distance(char[] str2, char[] s1sub) {
        int row = str2.length;
        int col = s1sub.length;
        int[][] dp = new int[row][col];
        // dp[i][j]的含义：
        // str2[0..i]仅通过删除行为变成s1sub[0..j]的最小代价
        // 可能性一：
        // str2[0..i]变的过程中，不保留最后一个字符(str2[i])，
        // 那么就是通过str2[0..i-1]变成s1sub[0..j]之后，再最后删掉str2[i]即可 -> dp[i][j] = dp[i-1][j] + 1
        // 可能性二：
        // str2[0..i]变的过程中，想保留最后一个字符(str2[i])，然后变成s1sub[0..j]，
        // 这要求str2[i] == s1sub[j]才有这种可能, 然后str2[0..i-1]变成s1sub[0..j-1]即可
        // 也就是str2[i] == s1sub[j] 的条件下，dp[i][j] = dp[i-1][j-1]
        dp[0][0] = str2[0] == s1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] == i - 1 || str2[i] == s1sub[0]) ? i : Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (str2[i] == s1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static String generateString(int len, int v) {
        int maxLen = (int) (Math.random() * (len + 1));
        maxLen = Math.max(maxLen, 3);
        char[] arr = new char[maxLen];
        for (int i = 0; i < maxLen; i++) {
            arr[i] = (char) (Math.random() * v + 'a');
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int str1len = 20;
        int str2len = 10;
        int v = 10;
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            String str1 = generateString(str1len, v);
            String str2 = generateString(str2len, v);
//            System.out.println(str1 + "   " + str2);
            int ans1 = ways1(str1, str2);
            int ans2 = ways1(str1, str2);
            if (ans1 != ans2) {
                System.out.println("fuck");
                System.out.println("ans1: " + ans1);
                System.out.println("ans2: " + ans1);
                System.out.println("str1: " + str1);
                System.out.println("str2: " + str2);
            }
        }
        System.out.println("test end");
    }
}
