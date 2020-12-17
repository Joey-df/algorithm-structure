package train_primary.class02.random;

/**
 * 01不等概率随机到01等概率随机
 */
public class Code02_RandToRand3 {


    // 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
    public static int noEqualP() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    // 等概率返回0和1
    public static int equalPReturn01() {
        int ans = 0;
        do {
            ans = noEqualP();
        } while (ans == noEqualP());
        return ans;
    }

    public static void main(String[] args) {
        int[] counts = new int[2];
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int num = equalPReturn01();
            counts[num]++;
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }
}
