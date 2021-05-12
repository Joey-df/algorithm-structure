package coding_for_great_offer.class01;

/**
 * 一个数组中只有两种字符'G'和’B’，
 * 可以让所有的G都放在左侧，所有的B都放在右侧
 * 或者可以让所有的G都放在右侧，所有的B都放在左侧
 * 但是只能在相邻字符之间进行交换操作，
 * 返回至少需要交换几次
 * <p>
 * 贪心
 */
public class Code04_MinSwapStep {

    //str: G B组成的字符串
    // BBGBGBGBGGBB
    public static int process(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        //（一）所以的G都放在左侧
        //贪心：第一个G放0位置，第二个G放1位置...，交换次数最少
        int ans1 = 0;
        int ans2 = 0;
        int gi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') {
                ans1 += (i - gi);
                gi++;
            }
        }
        //（二）所以的B都放在左侧
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'B') {
                ans2 += (i - bi);
                bi++;
            }
        }
        return Math.min(ans1, ans2);
    }

    public static int minSteps2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int step2 = 0;
        int gi = 0;
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') { // 当前的G，去左边   方案1
                step1 += i - (gi++);
            } else {// 当前的B，去左边   方案2
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    public static String geneStr(int maxLen) {
        int len = (int) (Math.random() * maxLen);
        char[] arr = new char[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int testTimes =500000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            String str = geneStr(maxLen);
            int ans1 = process(str);
            int ans2 = minSteps2(str);
            if (ans1!=ans2) {
                System.out.println("出错了！\n"+str);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
