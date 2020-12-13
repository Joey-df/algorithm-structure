package train_camp_04.class02;

/**
 * 给定一个数组arr，从左到右表示昨天从早到晚股票的价格。
 * 作为一个事后诸葛亮，你想知道如果只做一次交易，且每次交易只买卖一股，返回能挣到的最大钱数
 */
public class Code01_BestTimetoBuyandSellStock1 {

    //[4,2,1,4,1,4,16,75,3]
    public static int process(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int preMin = arr[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            preMin = Math.min(preMin, arr[i]);
            ans = Math.max(ans, arr[i] - preMin);
        }
        return Math.max(0, ans);
    }

    public static void main(String[] args) {
        System.out.println(process(new int[]{4, 2, 1, 4, 1, 4, 16, 75, 3}));
    }
}
