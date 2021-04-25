package questions;

/**
 * 一个圆环上有100个灯泡，灯泡有打开关闭两种状态，灯泡的状态随机，
 * 按一个灯泡，相邻的两个灯泡的状态也发生一次变化。
 * 比如 暗-亮-暗，按中间灯泡，变化为 亮-暗-亮。
 * 设计一个算法，使得所有灯泡全亮，至少要操作几次？
 */
public class Light {

	// 无环的
	public static int minSteps1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		// 不变0位置的状态
		int p1 = process1(arr, 2, arr[0], arr[1]);
		// 改变0位置的状态
		int p2 = process1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
		if (p2 != Integer.MAX_VALUE) {
			p2++;
		}
		return Math.min(p1, p2);
	}

	public static int process1(int[] arr, int nextIndex, int preStatus, int curStatus) {
		if (nextIndex == arr.length) {
			if (preStatus != curStatus) {
				return Integer.MAX_VALUE;
			}
			return curStatus == 1 ? 0 : 1;
		}
		// i < arr.length
		if (preStatus == 0) { // 一定要改变
			curStatus ^= 1;
			int cur = arr[nextIndex] ^ 1;
			int next = process1(arr, nextIndex + 1, curStatus, cur);
			return next == Integer.MAX_VALUE ? next : (next + 1);
		} else { // 一定不能改变
			return process1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
		}
	}

	// 有环的
	public static int minSteps2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		if (arr.length == 3) {
			return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		// 0不变，1不变
		int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
		// 0改变，1不变
		int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
		// 0不变，1改变
		int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
		// 0改变，1改变
		int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
		p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
		p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
		p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
		return Math.min(Math.min(p1, p2), Math.min(p3, p4));
	}

	public static int process2(int[] arr, int nextIndex, int preStatus, int curStatus, int endStatus, int firstStatus) {
		if (nextIndex == arr.length) {
			if (endStatus != firstStatus || endStatus != preStatus) {
				return Integer.MAX_VALUE;
			} else {
				return endStatus ^ 1;
			}
		}
		int curStay = (nextIndex == arr.length - 1) ? endStatus : arr[nextIndex];
		int curChange = (nextIndex == arr.length - 1) ? (endStatus ^ 1) : (arr[nextIndex] ^ 1);
		int endChange = (nextIndex == arr.length - 1) ? curChange : endStatus;
		if (preStatus == 0) {
			int next = process2(arr, nextIndex + 1, curStatus ^ 1, curChange, endChange, firstStatus);
			return next == Integer.MAX_VALUE ? next : (next + 1);
		} else {
			return process2(arr, nextIndex + 1, curStatus, curStay, endStatus, firstStatus);
		}
	}

	public static int right1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		return f1(arr, 0);
	}

	public static int f1(int[] arr, int i) {
		if (i == arr.length) {
			return valid(arr) ? 0 : Integer.MAX_VALUE;
		}
		int p1 = f1(arr, i + 1);
		change1(arr, i);
		int p2 = f1(arr, i + 1);
		change1(arr, i);
		p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
		return Math.min(p1, p2);
	}

	public static void change1(int[] arr, int i) {
		if (i == 0) {
			arr[0] ^= 1;
			arr[1] ^= 1;
		} else if (i == arr.length - 1) {
			arr[i - 1] ^= 1;
			arr[i] ^= 1;
		} else {
			arr[i - 1] ^= 1;
			arr[i] ^= 1;
			arr[i + 1] ^= 1;
		}
	}

	public static int right2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		return f2(arr, 0);
	}

	public static int f2(int[] arr, int i) {
		if (i == arr.length) {
			return valid(arr) ? 0 : Integer.MAX_VALUE;
		}
		int p1 = f2(arr, i + 1);
		change2(arr, i);
		int p2 = f2(arr, i + 1);
		change2(arr, i);
		p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
		return Math.min(p1, p2);
	}

	public static void change2(int[] arr, int i) {
		arr[lastIndex(i, arr.length)] ^= 1;
		arr[i] ^= 1;
		arr[nextIndex(i, arr.length)] ^= 1;
	}

	public static int lastIndex(int i, int N) {
		return i == 0 ? (N - 1) : (i - 1);
	}

	public static int nextIndex(int i, int N) {
		return i == N - 1 ? 0 : (i + 1);
	}

	public static boolean valid(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return false;
			}
		}
		return true;
	}

	public static int[] randomArray(int len) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 2);
		}
		return arr;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int testTime = 20000;
		int lenMax = 12;
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * lenMax);
			int[] arr = randomArray(len);
			int ans1 = right1(arr);
			int ans2 = minSteps1(arr);
			if (ans1 != ans2) {
				System.out.println("minSteps1 Oops!");
			}
		}
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * lenMax);
			int[] arr = randomArray(len);
			int ans1 = right2(arr);
			int ans2 = minSteps2(arr);
			if (ans1 != ans2) {
				System.out.println("minSteps2 Oops!");
			}
		}
		System.out.println("test end");

		int len = 20000;
		int[] arr = randomArray(len);
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		minSteps1(arr);
		end = System.currentTimeMillis();
		System.out.println("arr len: " + len + ", minSteps1 run time(ms): " + (end - start));

		start = System.currentTimeMillis();
		minSteps2(arr);
		end = System.currentTimeMillis();
		System.out.println("arr len: " + len + ", minSteps2 run time(ms): " + (end - start));
		System.out.println("更大的长度会导致栈溢出，但是可以轻易的改成迭代版，课上讲吧");
	}

}
