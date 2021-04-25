package questions;

import java.util.ArrayList;

public class Problem_0767_ReorganizeString {

	// 解法思路完全来自，leetcode 621 TaskScheduler
	public static String reorganizeString(String str) {
		char[] tasks = str.toCharArray();
		int[] count = new int[26];
		int maxCount = 0;
		int maxKinds = 0;
		ArrayList<Integer> maxTimesChs = new ArrayList<>();
		ArrayList<Integer> noMaxTimesChs = new ArrayList<>();
		for (char task : tasks) {
			count[task - 'a']++;
			maxCount = Math.max(maxCount, count[task - 'a']);
		}
		for (int i = 0; i < 26; i++) {
			if (count[i] == maxCount) {
				maxKinds++;
				maxTimesChs.add(i);
			} else {
				noMaxTimesChs.add(i);
			}
		}
		if ((maxCount - 1) * Math.max(0, 2 - maxKinds) - (tasks.length - maxCount * maxKinds) > 0) {
			return "";
		}
		int index = 0;
		ArrayList<StringBuilder> box = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < maxCount; i++) {
			box.add(new StringBuilder());
		}
		for (int i : maxTimesChs) {
			for (int j = 0; j < maxCount; j++) {
				box.get(j).append((char) (i + 'a'));
			}
		}
		for (int i : noMaxTimesChs) {
			for (int j = 0; j < count[i]; j++) {
				box.get(index).append((char) (i + 'a'));
				index = (index == maxCount - 2) ? 0 : (index + 1);
			}
		}
		for (StringBuilder cur : box) {
			builder.append(cur.toString());
		}
		return builder.toString();
	}

}
