package greedy;

import java.util.Arrays;

//coding_for_great_offer.class35;
// 来自小红书
// [0,4,7] ： 0表示这里石头没有颜色，如果变红代价是4，如果变蓝代价是7
// [1,X,X] ： 1表示这里石头已经是红，而且不能改颜色，所以后两个数X无意义
// [2,X,X] ： 2表示这里石头已经是蓝，而且不能改颜色，所以后两个数X无意义
// 颜色只可能是0、1、2，代价一定>=0
// 给你一批这样的小数组，要求最后必须所有石头都有颜色，且红色和蓝色一样多，返回最小代价
// 如果怎么都无法做到所有石头都有颜色、且红色和蓝色一样多，返回-1

//Drive问题和这个题像
//coding_for_great_offer.class02.Code04_Drive --> maxMoney3
public class Code02_MagicStone {

    public static int minCost(int[][] stones) {
        int n = stones.length;
        if ((n & 1) != 0) {
            return -1;
        }
        Arrays.sort(stones, (a, b) -> (a[0] == 0 && b[0] == 0) ? ((b[1] - b[2]) - (a[1] - a[2])) : (a[0] - b[0]));
        print(stones);
        int zero = 0;  //4
        int red = 0; //1
        int blue = 0; //1
        int cost = 0;
        for (int i = 0; i < n; i++) {
            if (stones[i][0] == 0) {
                zero++;
                cost += stones[i][1]; //cost初始: 无色的全变红的代价
            } else if (stones[i][0] == 1) {
                red++;
            } else {
                blue++;
            }
        }
        if (red > (n >> 1) || blue > (n >> 1)) {
            return -1; // 红和蓝，任何一个超过一半，直接-1（因为红、蓝都不能改变颜色）
        }
        System.out.println("cost: " + cost);
        int needToBlue = zero - ((n >> 1) - red); //需要变蓝的数量
        System.out.println("red: " + red);
        System.out.println("blue: " + blue);
        System.out.println("needToBlue: " + needToBlue);
        for (int i = 0; i < needToBlue; i++) {
            //cost += stones[i][2] - stones[i][1]; //与下面的等价
            cost -= stones[i][1] - stones[i][2];
        }
        return cost;
    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] stones = {
                {1, 5, 3},
                {2, 7, 9},
                {0, 6, 1},
                {0, 7, 9},
                {0, 3, 2},
                {0, 5, 9}};
        System.out.println(minCost(stones));
    }

}
