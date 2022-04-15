package coding_for_great_offer.class09;

/**
 * 定义何为step sum？
 * 比如680，680 + 68 + 6 = 754，680的step sum叫754
 * 给定一个正数num，判断它是不是某个数的step sum
 */
public class Code01_IsStepSum {

    //分析得知：
    //某个数的step sum，具有单调性
    //so，使用二分
    //时间复杂度：log2(num)*log10(k)
    public static boolean isStepNum(int num) {
        int l = 0;
        int r = num;
        while (l <= r) {
            int m = (l + r) / 2;
            if (f(m) == num) {
                //System.out.println(m);
                return true;
            } else if (f(m) > num) {
                r = m - 1; //左边找
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    //函数功能：
    //计算n的step num
    public static int f(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n;
            n /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(f(300));
        System.out.println(isStepNum(300));
    }
}
