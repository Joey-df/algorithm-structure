package routine;


public class LongestIncreaseSequence {

    //[2,1,4,2,3]
    public static int right(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[] dp = new int[N]; //dp[i]:LIS必须以i位置结尾，长度是多长
        dp[0] = 1;
        int ans = dp[0];
        for (int i = 1; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static int getLis(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        //dp[i]:LIS必须以i位置结尾，LIS是多长
        int[] dp = new int[N];
        //ends[i]:所有长度为i+1的LIS的最小结尾是啥
        int[] ends = new int[N];
        dp[0] = 1;
        ends[0] = arr[0];
        int ans = dp[0];
        int right = 0; //ends的有效区为[0,right]，有效区必有序
        for (int i = 1; i < N; i++) {
            int l = 0, r = right;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (ends[m] >= arr[i]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            //在[0,right]上找>=arr[i]最左的位置，找到了就是l位置，如果没找到l会来到right+1位置
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //生成长度为len，最大值为max的数组
    public static int[] genArr(int len, int max) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * max) + 1;
        }
        return ans;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 100;
        int testTimes = 50000;
        System.out.println("测试开始!");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = genArr(len, max);
            int ans1 = right(arr);
            int ans2 = getLis(arr);
            if (ans1 != ans2) {
                printArr(arr);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束!");
    }
}
