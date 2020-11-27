package train_camp_03.class01;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/56ab932abac44c8aabf0af75f598a0b4
 * 来源：牛客网
 * <p>
 * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。牛牛想知道他最少需要涂染几个正方形。
 * 如样例所示: s = RGRGR
 * 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
 * 可以使用预处理数组
 */
public class Code04_ColorLeftRight {

    //完全不用预处理数组
    //RGRGR 分割线从-1挨个枚举到N位置，变成 分割线R 右边G 需要改变的元素个数，整体求min
    //左边G的数量(要变成R) + 右边R的数量(要变成G) 每一步求min
    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //RGRGR
        char[] str = s.toCharArray();
        int rightRNum = 0;//分割线右边R的数量
        for (int i = 0; i < str.length; i++) {
            rightRNum += str[i] == 'R' ? 1 : 0;
        }
        int ans = rightRNum; //全变G(需要把R全变G)的情况
        int leftGNum = 0; //分割线左边G的数量
        for (int i = 0; i < str.length; i++) {
            //[0,i]全变R，[i+1,N-1]全变G
            //[0,1]全变R
            //[0,2]全变R
            //...
            //[0,N-1]全变R
            leftGNum += str[i] == 'G' ? 1 : 0;
            rightRNum -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, leftGNum + rightRNum);
        }
        return ans;
    }

    //on class
    // RGRGR -> RRRGG
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int rAll = 0;
        for (int i = 0; i < N; i++) {
            rAll += str[i] == 'R' ? 1 : 0;
        }
        int ans = rAll; // 如果数组所有的范围，都是右侧范围，都变成G
        int left = 0;
        for (int i = 0; i < N - 1; i++) { // 0..i 左侧 n-1..N-1
            left += str[i] == 'G' ? 1 : 0;
            rAll -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, left + rAll);
        }
        // 0...N-1 左全部 右无
        ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
        return ans;
    }

    private static String generateString(int maxLen) {
        int len = (int) (Math.random() * (maxLen + 1));
        char[] ans = new char[len];
        for (int i = 0; i < ans.length; i++) {
            int r = (int) (Math.random() * 10);
            ans[i] = ((r & 1) == 0) ? 'R' : 'G';
        }
        return String.valueOf(ans);
    }


    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = generateString(maxLen);
            int ans1 = process(str);
            int ans2 = minPaint(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println("fuck!!");
                break;
            }
        }
        System.out.println("test end");
    }
}
