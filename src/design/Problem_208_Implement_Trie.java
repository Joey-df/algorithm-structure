package design;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class Problem_208_Implement_Trie {

    private static class Node {
        private int pass;
        private int end;
        private Node[] paths;

        public Node() {
            pass = 0;
            end = 0;
            paths = new Node[26];
        }
    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        //向前缀树中添加一个word
        public void insert(String word) {
            if (word == null || "".equals(word) || word.length() == 0) {
                return;
            }
            Node ptr = root;//指针先来到root
            ptr.pass++;
            char[] str = word.toCharArray();
            for (char c : str) {
                if (ptr.paths[c - 'a'] == null) {
                    ptr.paths[c - 'a'] = new Node();
                }
                ptr = ptr.paths[c - 'a'];
                ptr.pass++;
            }
            ptr.end++;
        }

        //搜索指定的word在树中是否存在
        public boolean search(String word) {
            if (word == null || "".equals(word) || word.length() == 0) {
                return false;
            }
            Node ptr = root;
            char[] str = word.toCharArray();
            for (char c : str) {
                if (ptr.paths[c - 'a'] == null) { //走着走着没路了
                    return false;
                }
                ptr = ptr.paths[c - 'a'];
            }
            //ptr来到word的最后一个字符
            return ptr.end > 0;
        }

        //查询是否存在以prefix开头的单词
        public boolean startsWith(String prefix) {
            if (prefix == null || "".equals(prefix) || prefix.length() == 0) {
                return false;
            }
            Node ptr = root;
            char[] str = prefix.toCharArray();
            for (char c : str) {
                if (ptr.paths[c - 'a'] == null) {
                    return false;
                }
                ptr = ptr.paths[c - 'a'];
            }
            return ptr.pass > 0;
        }

        //删除
        public void delete(String word) {
            if (this.search(word)) {//存在才可以删
                Node ptr = root;
                ptr.pass--;
                char[] str = word.toCharArray();
                for (char c : str) {
                    ptr.paths[c - 'a'].pass--;
                    if (ptr.paths[c - 'a'].pass == 0) {
                        ptr.paths[c - 'a'] = null;
                        return;
                    }
                    ptr = ptr.paths[c - 'a'];
                }
                ptr.end--;
            }
        }
    }

    public static void main(String[] args) {
        Trie tree = new Trie();
        tree.insert("abc");
        tree.insert("abd");
        tree.insert("abe");
        System.out.println(tree.search("abc"));
        tree.delete("ab");
        System.out.println(tree.search("abc"));
        System.out.println(tree.startsWith("abo"));
    }

}

