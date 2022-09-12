package data_structure.unionfind;

//547. 省份数量
//有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//
//给你一个 n x n 的矩阵 isConnected ，
//其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
//
//返回矩阵中 省份 的数量。
public class Problem_0547_NumberOfProvinces {

    public static void main(String[] args) {
        int[][] m = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int num = findCircleNum(m);
        System.out.println(num);
    }

    public static int findCircleNum(int[][] matrix) {
        int n = matrix.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.size();
    }

    public static class UnionFind {
        private int[] size; //size[i]=k: i为代表节点，才有意义，否则无意义
        private int[] parents; //parents[i]=k: i的父亲是k
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            size = new int[n];
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parents[i] = i;
            }
            help = new int[n];
            sets = n;
        }

        //返回i的代表节点
        public int find(int i) {
            int p = 0;
            while (i != parents[i]) {
                help[p++] = i;
                i = parents[i];
            }
            //i来到代表节点
            for (p--; p >= 0; p--) {
                parents[help[p]] = i;
            }
            return i;
        }

        //i j是否为一个集合
        public boolean same(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int a, int b) {
            int f1 = find(a);
            int f2 = find(b);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    parents[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parents[f1] = f2;
                    size[f2] += size[f1];
                }
                sets--;
            }
        }

        public int size() {
            return sets;
        }

    }
}
