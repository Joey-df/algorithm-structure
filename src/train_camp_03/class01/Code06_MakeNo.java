package train_camp_03.class01;

/**
 * 给定一个正整数M，请构造出一个长度为M的数组arr，要求
 * 对任意的i、j、k三个位置，如果i < j < k，都有arr[i] + arr[k] != 2*arr[j]
 * 返回构造出的arr
 * <p>
 * 时间复杂度 O(N)
 * master公式的应用 T(N)=T(N/2)+O(N) ==> O(N)
 */
public class Code06_MakeNo {

    public static int[] constructArr(int M) {
        if (M == 1) {
            return new int[]{1};
        }
        //M>1
        int halfSize = (M + 1) >> 1; //M一半的长度
        int[] halfArr = constructArr(halfSize); //分治构造一半长度的满足要求的数组
        //利用halfArr构造出整体的数组
        int[] res = new int[M];
        for (int i = 0; i < halfSize; i++) {
            res[i] = halfArr[i] * 2 - 1; //第i个奇数
        }
        int index = 0;
        for (int i = halfSize; i < M; i++) {
            res[i] = halfArr[index++] * 2; //第i个偶数
        }
        return res;
    }

    public static boolean isValid(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (nums[i] + nums[k] == 2 * nums[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //for test
    public static void main(String[] args) {
        int testTime = 100000;
        int maxLen = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int M = (int) (Math.random() * (maxLen + 1)) + 3;
            M = Math.max(M, 3);
            int[] nums = constructArr(M);
            if (!isValid(nums)) {
                System.out.println("fuck");
            }
        }
        System.out.println("test end");
    }
}
