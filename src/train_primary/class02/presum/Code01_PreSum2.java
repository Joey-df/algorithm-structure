package train_primary.class02.presum;

/**
 * 数组的前缀和
 * 刷题经常用到的预处理技巧
 * <p>
 * 方法二：预处理数组(一维)，得到一维数组preSum，计算arr[l,r]累加和时通过减法运算得到结果
 */
public class Code01_PreSum2 {

    //根据原始数组得到前缀和数组
    public static int[] getPreSum(int[] arr) {
        //preSum[i]表示arr[0,i]范围上的累加和
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        return preSum;
    }

    // 求arr[l,r]范围上的累加和
    public static int rangeSum(int[] arr, int l, int r) {
        int[] preSum = getPreSum(arr);
        return l == 0 ? preSum[r] : preSum[r] - preSum[l - 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 6, 7};
        System.out.println(rangeSum(arr, 0, 3));
    }
}
