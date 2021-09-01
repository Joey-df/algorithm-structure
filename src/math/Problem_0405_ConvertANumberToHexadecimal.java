package math;

/**
 * 405. 数字转换为十六进制数
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 * <p>
 * 注意:
 * <p>
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；
 * 对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * <p>
 * 示例 1：
 * 输入:
 * 26
 * 输出:
 * "1a"
 * <p>
 * 示例 2：
 * 输入:
 * -1
 * 输出:
 * "ffffffff"
 */
public class Problem_0405_ConvertANumberToHexadecimal {

    // Basic idea: each time we take a look at the last four digits of
    // binary verion of the input, and maps that to a hex char
    // shift the input to the right by 4 bits, do it again
    // until input becomes 0.

    /**
     * For example, num = 4012
     * Step 1) What is 4012 in binary number?
     * 00000000000000000000111110101100
     * <p>
     * Step 2) How to convert a binary to hex presentation? We separate the binary in 8 groups, each group has 4 bits.
     * 0000 0000 0000 0000 0000 1111 1010 1100
     * <p>
     * Step 3) We create a while loop to read 4 bits each time. Since it's a 32-bit Integer, we can only execute this while loop 8 times. We also want to exit the while loop if the num is 0.
     * while (loopCount < 8 && num != 0)
     * <p>
     * Step 4) How to read 4 bits each time?
     * We can use & operation. 15 in binary is 1111. If we do num & 15, it will give you the last four bits.
     * num & 15 will give you the number in range [0...15], we can use this to map to the hexChar array.
     * 0 -> 0 ....9 -> 9....10 -> a....15 -> f
     * After each loop, we need to remove the last 4 bits. num = num >> 4, and also increment the loopCount by 1.
     * <p>
     * This apporach can also handle the negative case.
     * For example -1 in binary is 1111 1111 1111 1111 1111 1111 1111 1111. We just apply the same logic above.
     */
    public String toHex(int num) {
        if (num == 0) return "0";
        //以下为非0的统一处理逻辑
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuilder sb = new StringBuilder();
        int group = 0;
        while (group < 8 && num != 0) {
            int last4 = num & 15; //末尾4位
            sb.append(map[last4]);
            num = num >>> 4; //右移4位 （使用>>也可以）
            group++;
        }
        return sb.reverse().toString();
    }
}
