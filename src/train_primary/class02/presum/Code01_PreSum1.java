package train_primary.class02.presum;

/**
 * 数组的前缀和
 * 刷题经常用到的预处理技巧
 * <p>
 * 方法一：暴力方法
 */
public class Code01_PreSum1 {

    //给定arr，求arr[l,r]范围上的累加和
    //暴力方法 每次都使用for循环累加得到结果
    public static int rangeSum(int[] arr, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum;
    }
}

