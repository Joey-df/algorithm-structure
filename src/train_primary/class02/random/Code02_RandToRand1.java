package train_primary.class02.random;


public class Code02_RandToRand1 {

    // Math.random()返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x
    // 调整成x平方 --> max
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    // Math.random()返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x
    // 调成1-(1-x)^2 --> min
    public static double xToXPower2FollowUp() {
        return Math.min(Math.random(), Math.random());
    }

    // Math.random()返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x
    // 调整成x三次方
    public static double xToXPower3() {
        return Math.max(Math.random(), Math.max(Math.random(), Math.random()));
    }


    public static void main(String[] args) {
        System.out.println("=====xToXPower2测试====");

        int count = 0;
        int testTimes = 10000000;
        double x = 0.5;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));

        System.out.println("==========");

        System.out.println("=====xToXPower3测试====");

        count = 0;
        testTimes = 10000000;
        x = 0.5;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower3() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 3));

        System.out.println("==========");

        System.out.println("=====xToXPower2FollowUp测试====");

        count = 0;
        testTimes = 10000000;
        x = 0.5;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2FollowUp() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 1 - Math.pow((double) 1 - x, 2));

        System.out.println("==========");
    }
}
