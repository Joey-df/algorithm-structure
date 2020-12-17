package train_primary.class01;

/**
 * 打印一个数的二进制形式
 */
public class Code01_PrintBinary {

    public static void print(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append(((num) & (1 << i)) == 0 ? '0' : '1');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // 32位
//		int num = 4;
//
//		print(num);
//
//
//		int test = 1123123;
//		print(test);
//		print(test<<1);
//		print(test<<2);
//		print(test<<8);
//
//
//		int a = Integer.MAX_VALUE;
//		System.out.println(a);

//		print(-1);
//		int a = Integer.MIN_VALUE;
//		print(a);

//		int b = 123823138;
//		int c = ~b;
//		print(b);
//		print(c);

//		print(-5);

//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);

//		int a = 12319283;
//		int b = 3819283;
//		print(a);
//		print(b);
//		System.out.println("=============");
//		print(a | b);
//		print(a & b);
//		print(a ^ b);

		int a = Integer.MIN_VALUE;
		print(a);
		print(a >> 1); // >> 有符号右移，将运算数的二进制整体右移指定位数，正数高位用0补齐，负数高位用1补齐（保持负数符号不变）。
		print(a >>> 1); // >>>，无符号右移位，不管正数还是负数，高位都用0补齐（忽略符号位）
//
//		int c = Integer.MIN_VALUE;
//		int d = -c ;
//
//		print(c);
//		print(d);

    }

}
