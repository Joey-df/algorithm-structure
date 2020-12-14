package train_camp_04.class03;

/**
 * 给定一个数组arr，如果有某个数出现次数超过了数组长度的一半，打印这个数，如果没有不打印
 * <p>
 * 给定一个数组arr和整数k，arr长度为N，如果有某些数出现次数超过了N/K，打印这些数，如果没有不打印
 * <p>
 * 著名的水王问题
 */
public class Code05_FindKMajority {

    //建立一次删掉两个不同数的机制
    public static int process(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new RuntimeException("arr is invalid.");
        }
        int cand = 0;
        int HP = 0;
        for (int i = 0; i < arr.length; i++) {
            if (HP == 0) { //立靶子
                cand = arr[i];
                HP = 1;
            } else if (arr[i] == cand) {
                HP++;
            } else {
                HP--;
            }
        }
        if (HP == 0) {
            throw new RuntimeException("no such number");
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == cand) {
                count++;
            }
        }
        if (count > arr.length >> 1) {
            return cand;
        } else {
            throw new RuntimeException("no such number");
        }
    }

    public static void main(String[] args) {
        System.out.println(process(new int[]{1, 1, 2, 2, 2}));
    }
}
