package train_primary.class05;

import java.util.HashSet;

public class Code02_BitMap2 {

	// 这个类的实现是正确的
	public static class BitMap {

		private long[] bits;

		public BitMap(int max) {
			//根据最大值max，决定bits这个long数组应该开多长
			//(max + 64) >> 6  =>   (max + 64) / 64
			//比如比如170应该放在 bits[2]
			bits = new long[(max + 64) >> 6];
		}

		public void add(int num) {
			//1、num >> 6 等价于 num/64，即找到num应该放在bits中那个位置
			//2、num & 63 等价于 num % 64，表示在bits对应64位整数bits[i]的第几位应该标1
			//3、比如170应该放在bits[2]这个整数的 第42位（17%64 = 42）
			bits[num >> 6] |= (1L << (num & 63));
		}

		public void delete(int num) {
			//删170，等同于bits[2]的第42位 改为0
			bits[num >> 6] &= ~(1L << (num & 63));
		}

		public boolean contains(int num) {
			return (bits[num >> 6] & (1L << (num & 63))) != 0;
		}

	}

	public static void main(String[] args) {
		int a=324124123;
		System.out.println(a%128);
		System.out.println(a&127);
		System.out.println(170%64);
		System.out.println(1L << 42);
		System.out.println("测试开始！");
		int max = 10000;
		BitMap bitMap = new BitMap(max);
		HashSet<Integer> set = new HashSet<>();
		int testTime = 10000000;
		for (int i = 0; i < testTime; i++) {
			int num = (int) (Math.random() * (max + 1));
			double decide = Math.random();
			if (decide < 0.333) {
				bitMap.add(num);
				set.add(num);
			} else if (decide < 0.666) {
				bitMap.delete(num);
				set.remove(num);
			} else {
				if (bitMap.contains(num) != set.contains(num)) {
					System.out.println("Oops!");
					break;
				}
			}
		}
		for (int num = 0; num <= max; num++) {
			if (bitMap.contains(num) != set.contains(num)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束！");
	}

}