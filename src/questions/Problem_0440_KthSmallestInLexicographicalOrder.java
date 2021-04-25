package questions;

public class Problem_0440_KthSmallestInLexicographicalOrder {

	public static int[] k0 = { 0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };

	public static int[] k1 = { 0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111, 111111111, 1111111111 };

	public static int findKthNumber(int n, int k) {
		int bits = bits(n);
		int offset = k0[bits];
		int first = n / offset;
		int every = k1[bits];
		int left = (first - 1) * every;
		if (k <= left) {
			int prefix = ((k - 1) / every) + 1;
			int pre = (prefix - 1) * every;
			return noLimitKth(prefix, bits, k - pre);
		}
		k -= left;
		int mid = limitAll(n, bits);
		if (k <= mid) {
			return limitKth(n, bits, k);
		}
		k -= mid;
		bits--;
		every /= 10;
		int prefix = ((k - 1) / every) + first + 1;
		int pre = (prefix - first - 1) * every;
		return noLimitKth(prefix, bits, k - pre);
	}

	// 求num十进制有几位
	public static int bits(int num) {
		int bits = 0;
		while (num != 0) {
			num /= 10;
			bits++;
		}
		return bits;
	}

	public static int limitAll(int max, int bits) {
		return k1[bits - 1] + (max % k0[bits]) + 1;
	}

	public static int noLimitKth(int prefix, int bits, int kth) {
		for (bits--, kth--; bits > 0 && kth > 0; bits--, kth--) {
			int all = k1[bits];
			int curNum = (kth - 1) / all;
			prefix = prefix * 10 + curNum;
			kth -= curNum * all;
		}
		return prefix;
	}

	public static int limitKth(int max, int bits, int kth) {
		int offset = k0[bits];
		int prefix = max / offset;
		boolean followMax = true;
		for (bits--, kth--, max %= offset, offset /= 10; kth > 0; bits--, kth--, max %= offset, offset /= 10) {
			int every = k1[bits];
			if (!followMax) {
				int curNum = (kth - 1) / every;
				prefix = prefix * 10 + curNum;
				kth -= curNum * every;
			} else {
				int maxBitsNum = offset > max ? 0 : (max / offset);
				int left = maxBitsNum * every;
				if (kth <= left) {
					followMax = false;
					int curNum = (kth - 1) / every;
					prefix = prefix * 10 + curNum;
					kth -= curNum * every;
				} else {
					kth -= left;
					int mid = limitAll(max, bits);
					if (kth <= mid) {
						prefix = prefix * 10 + maxBitsNum;
					} else {
						followMax = false;
						kth -= mid;
						bits--;
						every /= 10;
						int curNum = (kth - 1) / every + maxBitsNum + 1;
						prefix = prefix * 10 + curNum;
						kth -= (curNum - maxBitsNum - 1) * every;
					}
				}
			}
		}
		return prefix;
	}

}
