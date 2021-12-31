package data_structure;

/**
 * 470. 用 Rand7() 实现 Rand10()
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 * 不要使用系统的 Math.random() 方法。
 * 示例 1:
 * 输入: 1
 * 输出: [7]
 * 示例 2:
 * 输入: 2
 * 输出: [8,4]
 * 示例 3:
 * 输入: 3
 * 输出: [8,1,10]
 * 提示:
 * rand7 已定义。
 * 传入参数: n 表示 rand10 的调用次数。
 * 进阶:
 * rand7()调用次数的 期望值 是多少 ?
 * 你能否尽量少调用 rand7() ?
 */
public class Problem_0470_ImplementRand10UsingRand7 {

    // 此函数为系统提供，只能用，不能修改
    // 等概率返回1~7
    public static int rand7() {
        return (int)(Math.random() * 7) + 1;
    }

    //方法1：
    //rand7() -> rand49() -> rand40() -> rand10()
    public static int rand10() {
        int row, col, point;
        do {
            row = rand7();
            col = rand7();
            point = col + (row - 1) * 7;
        } while (point > 40);
        return 1 + (point - 1) % 10;
    }

    //方法2：
    // 1、先利用rand7()构造一个01发生器f1()；
    // 2、确定二进制位数，1～10需要4位，构造0 ~ 15等概率返回返回f2()
    // 3、利用f2()构造0 ~ 9等概率返回函数f3()
    // 4、利用f3()构造1 ~ 10等概率返回函数fun()
    // 随机机制，只能用rand7，
    // 等概率返回0和1 (使用f1函数生成等概率的01发生器)
    public static int f1() {
        int ans = 0;
        do {
            ans = rand7();
        } while (ans == 4); //1 2 3 4 5 6 7,中位数是4（如果是偶数长度，前面一半返回0，后面一半返回1）
        return ans < 4 ? 0 : 1;
    }

    // 得到0000 ~ 1111 做到等概率 0 ~ 15等概率返回一个
    public static int f2() {
        return (f1() << 3) + (f1() << 2) + (f1() << 1) + (f1() << 0);
    }

    // 0 ~ 9等概率返回一个
    public static int f3() {
        int ans = 0;
        do {
            ans = f2();
        } while (ans > 9); // >=10的话，重做
        return ans;
    }

    // 等概率返回1 ~ 10
    public static int fun() {
        return f3() + 1;
    }

}
