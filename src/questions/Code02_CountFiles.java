package questions;

import java.io.File;
import java.util.Stack;

public class Code02_CountFiles {

    // 注意这个函数也会统计隐藏文件
    public static int getFileNumber(String folderPath) {
        // File   (文件夹、文件)
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        // File  文件夹  文件   stack只放文件夹
        Stack<File> stack = new Stack<>();
        stack.add(root);
        int files = 0;
        while (!stack.isEmpty()) {
            File folder = stack.pop();
            for (File next : folder.listFiles()) {
                if (next.isFile()) {
                    files++;
                }
                if (next.isDirectory()) {
                    stack.push(next);
                }
            }
        }
        return files;
    }

    //递归实现
    //递归含义：path目录下有多少个文件返回
    public static int ways2(String path) {
        File root = new File(path);
        if (!root.isFile() && !root.isDirectory()) {
            return 0;
        }
        if (root.isFile()) return 1;
        //是目录
        return process(root);
    }

    //返回path下文件总数
    //path一定是目录
    public static int process(File path) {
        File[] files = path.listFiles();
        int ans = 0;
        for (File file : files) {
            if (file.isFile()) {
                ans++;
            } else if (file.isDirectory()) {
                ans += process(file);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // 你可以自己更改目录
        String path = "/Users/admin/Downloads";
        System.out.println(getFileNumber(path));
        System.out.println(ways2(path));
    }

}
