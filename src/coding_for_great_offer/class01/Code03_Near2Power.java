package coding_for_great_offer.class01;

/**
 * 给定一个非负整数num，
 * 如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 * (新增题)
 */
public class Code03_Near2Power {

    public static int process(int n) {
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }

    //方法二
    public static int nextPowerOf2(int n) {
        if (n <= 0) return 1;
        int highestOneBit = Integer.highestOneBit(n);
        return (highestOneBit == n) ? n : highestOneBit << 1;
    }

    public static void main(String[] args) {
        System.out.println(process(0));
        System.out.println(nextPowerOf2(0));
    }
}
