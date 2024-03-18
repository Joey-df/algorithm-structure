package data_structure.stack_queue;

//使用数组实现固定大小的栈
public class Code04_ArrayImplementStack {

    public static class MyStack {
        private final int limit;
        private int size;
        private final int[] arr;

        public MyStack(int limit) {
            this.limit = limit;
            this.size = 0;
            this.arr = new int[limit];
        }

        //入栈
        public void push(int val) {
            if (isFull()) {
                throw new RuntimeException("栈已满，不能再加入元素！");
            }
            this.arr[size++] = val;
        }

        //弹栈
        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈已空，无元素可以弹出！");
            }
            //返回top-1位置的元素，然后top--；
            //写成一行就是this.arr[--top]
            return this.arr[--size];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        //栈中元素数量
        public int size() {
            return size;
        }

        //获取栈顶元素，只是查看，不删除
        public int peak() {
            if (isEmpty()) {
                throw new RuntimeException("栈已空，无元素可以弹出！");
            }
            return this.arr[size - 1];
        }

        //清空栈
        public void clear() {
            while (size != 0) {
                pop();
            }
        }
    }
}
