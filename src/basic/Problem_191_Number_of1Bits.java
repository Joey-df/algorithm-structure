package basic;

/**
 * 一个数二进制形式中1的个数
 */
public class Problem_191_Number_of1Bits {

    public static int countBit(int N) {
        int count = 0;
        while (N != 0) {
            int rightOne = N & -N; // int rightOne = N & (~N + 1);
            count++;
            N ^= rightOne;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countBit(33));
    }
}
