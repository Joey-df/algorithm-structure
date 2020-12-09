package train_camp_03.class06;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 * 例如:
 * arr=[3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2] 返回true
 * 三个切割点下标为2, 5, 7. 切出的四个子数组为[3,2], [1,4], [5], [1,2,2]，
 * 累加和都是5
 */
public class Code05_Split4Parts {

    public static boolean process(int[] arr) {
        //如果数组长度小于7，不可能切出4段
        if (arr == null || arr.length < 7) {
            return false;
        }
        //先求累加和map
        //key：index位置之前的累加和sum
        //value：index
        Map<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }

        int leftSum = arr[0]; //第一刀左侧的累加和
        //枚举第一刀的位置 从1位置开始，最右只能到arr.length-6位置
        for (int cut1s = 1; cut1s < arr.length - 5; cut1s++) {
            int checkSum = leftSum * 2 + arr[cut1s];
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum);
                checkSum += leftSum + arr[s2];
                if (map.containsKey(checkSum)) {
                    int s3 = map.get(checkSum);
                    if (checkSum + arr[s3] + leftSum == sum) {
                        return true;
                    }
                }
            }
            leftSum += arr[cut1s];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4, 1, 4, 9, 5, 10, 1, 2, 2};
        System.out.println(process(arr));
    }
}
