package coding_for_great_offer.class37;
//train_camp_03.class08.Code02_MoneyWays -> coinCharge2
public class Problem_0416_PartitionEqualSubsetSum {

	//前提 nums是正数数组
	public static boolean canPartition(int[] nums) {
		int N = nums.length;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += nums[i];
		}
		if ((sum & 1) != 0) {
			return false;

		}
		sum >>= 1;
		//dp[i][j]含义：nums[0,i]范围上自由选择 是否能组成累加和j
		boolean[][] dp = new boolean[N][sum + 1];
		//第一列
		for (int i = 0; i < N; i++) {
			dp[i][0] = true; //什么数也不选，能组成0
		}
		//第一行
		for (int j = 0; j<= sum; j++) {
			dp[0][j] = nums[0] == j; //nums[0,0] 能否组成j
		}
		/*if (nums[0] <= sum) {
			dp[0][nums[0]] = true;
		}*/
		//普通位置
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j]; //不要nums[i]
				if (j - nums[i] >= 0) { //要nums[i] 即 nums[0,i-1] 组成 j-nums[i]
					dp[i][j] |= dp[i - 1][j - nums[i]];
				}
			}
			if (dp[i][sum]) {
				return true;
			}
		}
		return false;
	}

}