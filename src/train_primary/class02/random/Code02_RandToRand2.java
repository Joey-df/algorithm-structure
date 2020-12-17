package train_primary.class02.random;

/**
 * 从1~5随机到1~7随机
 * 可以推广到 从a~b随机到c~d随机
 */
public class Code02_RandToRand2 {

    // 此函数只能用，不能修改
    // 等概率返回1~5
    public static int f1() {
        return (int) (Math.random() * 5) + 1;
    }

    // 随机机制，只能用f1，
    // 等概率返回0和1 (使用f1函数生成等概率的01发生器)
    public static int f2() {
        int ans = 0;
        do {
            ans = f1();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 得到000 ~ 111 做到等概率 0 ~ 7等概率返回一个
    public static int f3() {
        return (f2() << 2) + (f2() << 1) + (f2() << 0);
    }

    // 0 ~ 6等概率返回一个
    public static int f4() {
        int ans = 0;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    // 等概率返回1~7
    public static int g() {
        return f4() + 1;
    }

    public static void main(String[] args) {
        int count = 0;
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            if (f2() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("==========");

        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int num = g();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }
}
