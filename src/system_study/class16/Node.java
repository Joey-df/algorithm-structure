package system_study.class16;

import java.util.ArrayList;

// 点结构的描述
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts; // 从我自己出发能直接到的邻居
    public ArrayList<Edge> edges; // 从我自己出发的边

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}