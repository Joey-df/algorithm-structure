package routine;


//每天一遍manacher
public class Manacher {

    //to manacher string  121  #1#2#1#
    public static char[] manacherString(String s) {
        int N = s.length();
        char[] res = new char[N << 1 | 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : s.charAt(index++);
        }
        return res;
    }

    //求s中的最长回文串长度
    public static int longest(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] mStr = manacherString(s);
        int[] pArr = new int[mStr.length];
        int R = 0; //回文右边界 表示合法右边界de下一个位置
        int C = -1;//取得回文右边界的回文中心
        int max = 0;
        for (int i = 0; i < mStr.length; i++) {
            //最短的不用验证的长度 i==R表示i在R外  i>=R表示i在R外
            //pArr[2 * C - i]：i的对称点的回文区域完全在[L,R]内部
            //R - i：i的对称点的回文区域的左边界跑到L的左边 或者 与L重合
            pArr[i] = R <= i ? 1 : Math.min(R - i, pArr[2 * C - i]);
            while (i - pArr[i] >= 0 && i + pArr[i] < mStr.length) {
                if (mStr[i - pArr[i]] == mStr[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
//            if (pArr[i]-i==1) { // 求在开头至少添加几个字符整体变回文
            if (R == mStr.length) { // 求在末尾至少添加几个字符整体变回文
                max = Math.max(max, pArr[i]);
                break;
            }
        }
        return max - 1;
    }


    public static void main(String[] args) {
        System.out.println(String.valueOf(manacherString("123sdasd")));
        System.out.println(longest("0012321a0a12321asa"));
    }
}
