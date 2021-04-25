package array;

/**
 * 华为面试题
 * 给定一个整型数组arr，和一个整数v，求平均值小于等于v的所有子数组中，最大长度是多少
 */
public class AvgLessEqualValueLongest {

    // 暴力解，时间复杂度O(N^3)，用于做对数器
    public static int ways1(int[] arr, int v) {
        int ans = 0;
        for (int L = 0; L < arr.length; L++) {
            for (int R = L; R < arr.length; R++) {
                int sum = 0;
                int k = R - L + 1;
                for (int i = L; i <= R; i++) {
                    sum += arr[i];
                }
                double avg = (double) sum / (double) k;
                if (avg <= v) {
                    ans = Math.max(ans, k);
                }
            }
        }
        return ans;
    }

    // 想实现的解，请填写 O(N^2)
    public static int ways2(int[] arr, int v) {
        int ans = 0;
        for (int L = 0; L < arr.length; L++) {
            int sum = 0;
            for (int R = L; R < arr.length; R++) {
                int len = R - L + 1;
                sum += arr[R];
                if (sum <= v * len) {
                    ans = Math.max(ans, len);
                }
            }
        }
        return ans;
    }

    // 用于测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    // 用于测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 用于测试
    public static void main(String[] args) {
        System.out.println("测试开始");
        int maxLen = 20;
        int maxValue = 100;
        int testTime = 500000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int value = (int) (Math.random() * maxValue);
            int ans1 = ways1(arr, value);
            int ans2 = ways2(arr, value);
            if (ans1 != ans2) {
                System.out.println("测试出错了！");
                System.out.print("测试数组：");
                printArray(arr);
                System.out.println("子数组平均值不小于：" + value);
                System.out.println("方法1得到的最大长度：" + ans1);
                System.out.println("方法2得到的最大长度：" + ans2);
                System.out.println("======");
            }
        }
        System.out.println("测试结束");
    }

}
