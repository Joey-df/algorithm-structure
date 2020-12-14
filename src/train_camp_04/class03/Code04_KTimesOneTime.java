package train_camp_04.class03;

/**
 * 给定一个数组arr，已知除了一种数只出现1次之外，剩下所有的数都出现了k次，如何使用O(1)的额外空间，找到这个数。
 */
public class Code04_KTimesOneTime {

    //分析：使用K进制
    //将每个十进制数变成K进制数，按位累加起来，最后%K之后，剩下的数就是只出现1次的数
    public static int findNum(int[] arr, int K) {
        int[] nums = new int[32];
        for (int i = 0; i < arr.length; i++) {
            add(nums, translateToKsys(arr[i], K));
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] %= K;
        }
        return translateFromKsys(nums, K);
    }

    public static void add(int[] nums, int[] cur) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] += cur[i];
        }
    }

    //十进制转成K进制
    public static int[] translateToKsys(int num, int K) {
        int[] res = new int[32]; //二进制是32位，K>2时，事实上用不了32位
        int index = 0;
        while (num != 0) {
            res[index++] = num % K;
            num = num / K;
        }
        return res;
    }

    //K进制转成十进制
    public static int translateFromKsys(int[] arr, int K) {
        int res = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            res = res * K + arr[i];
        }
        return res;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] test1 = {1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9};
        System.out.println(findNum(test1, 3));
    }
}
