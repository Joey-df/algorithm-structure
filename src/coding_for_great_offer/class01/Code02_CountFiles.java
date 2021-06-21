package coding_for_great_offer.class01;

import java.io.File;
import java.util.LinkedList;

/**
 * 给定一个文件目录的路径，
 * 写一个函数统计这个目录下所有的文件数量并返回
 * 隐藏文件也算，但是文件夹不算
 * (新增题)
 */
public class Code02_CountFiles {

    //返回给定path下所有文件的数量
    //隐藏文件也算
    //文件夹不算一个文件
    public static int countFiles(String path) {
        File root = new File(path);
        //既不是文件，也不是目录
        if (!root.isFile() && !root.isDirectory()) {
            return 0;
        }
        //是文件
        if (root.isFile()) {
            return 1;
        }
        //是目录
        LinkedList<File> q = new LinkedList<>();//队列中只存目录
        q.offerLast(root);
        int ans = 0;
        //宽度优先遍历
        while (!q.isEmpty()) {
            File[] folder = q.pollFirst().listFiles();
            for (File file : folder) {
                if (file.isFile()) {
                    ans++;
                } else {//是目录
                    q.offerLast(file);
                }
            }
        }
        return ans;
    }

    //递归实现
    public static int ways2(String path) {
        if (path == null || path.length() == 0) return 0;
        File root = new File(path);
        if (!(root.isFile() || root.isDirectory())) {
            return 0;
        }
        //是文件或目录
        return proccess(root);
    }

    //递归含义：返回root目录下文件的总数
    //root是文件或目录
    public static int proccess(File root) {
        //base case: root是文件
        if (root.isFile()) return 1;
        //root是目录
        File[] files = root.listFiles();
        int ans = 0;
        for (File file : files) {
            ans += proccess(file);
        }
        return ans;
    }

    public static void main(String[] args) {
        String path = "/Users/admin/Downloads";
        System.out.println(countFiles(path));
        System.out.println(ways2(path));
    }
}
