package math;

/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * 提示：
 * -10^7 <= num <= 10^7
 */
public class Problem_0504_Base7 {

    public static String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean neg = ((num >>> 31) & 1) == 1;
        if (neg) num = -num; //负数变成正数
        int[] arr = getKSysFromNum(num, 7);
        print(arr);
        StringBuilder sb = new StringBuilder();
        int start = 31;
        for (; start >= 0; start--) {
            if (arr[start] != 0) {
                break;
            }
        }
        for (int i = start; i >= 0; i--) {
            sb.append(arr[i]);
        }
        return neg ? "-" + sb.toString() : sb.toString();
    }

    //十进制数n转为k进制数
    public static int[] getKSysFromNum(int n, int k) {
        int[] res = new int[32]; //0是最低位
        int i = 0;
        while (n != 0) {
            res[i++] = n % k;
            n = n / k;
        }
        return res;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(-100));
    }

}
