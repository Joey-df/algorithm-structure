package greedy;

import java.util.Arrays;

//455. 分发饼干
//https://leetcode-cn.com/problems/assign-cookies/
public class Problem_0455_AssignCookies {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0;
        for (int j = 0; j < s.length && i < g.length; j++) {
            if (g[i] <= s[j]) i++;
        }
        return i;
    }

}
