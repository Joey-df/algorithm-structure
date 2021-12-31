package coding_for_great_offer.class01;

/**
 * 给定一个非负整数num，
 * 如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 * (新增题)
 */
public class Code03_Near2Power {
    //方法1
    public static int fun(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }

    //方法2:from spark 源码
    public static int nextPowerOf2(int n) {
        if (n <= 0) return 1;
        int highestOneBit = Integer.highestOneBit(n);
        return (highestOneBit == n) ? n : highestOneBit << 1;
    }

    //方法3：x为正数
    //from flink源码
    public static int roundUpToPowerOfTwo(int x) {
        x = x - 1;
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return x + 1;
    }

    //<=value,离value最近的2的某次方
    //from flink源码 MathUtils
    public static int roundDownToPowerOf2(int value) {
        return Integer.highestOneBit(value);
    }

    public static void main(String[] args) {
        System.out.println(fun(3));
        System.out.println(nextPowerOf2(3));
        System.out.println(roundUpToPowerOfTwo(3));
    }
}
