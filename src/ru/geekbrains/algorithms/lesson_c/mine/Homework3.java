package ru.geekbrains.algorithms.lesson_c.mine;

public class Homework3 {
    private static class Stack {
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new int[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(int i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public int pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }

    }

    private static String stringRevers(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        // Заполняем стек буковками из нашей строки.
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            st.push(ch);
        }
        // Вынимаем из стека в массив char все символы подряд.
        char[] output = new char[size];
        for (int i = 0; i < size; i++) {
            output[i] = (char) st.pop();
        }
        // Превращаем массив char в строку и возвращаем.
        return new String(output);
    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }

    private static class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int initialCapacity) {
            queue = new int[initialCapacity];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;
            queue[++tail] = i;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

    }

    private static class Deque {
        private int[] deque;
        private int head;
        private int tail;
        private int capacity;

        public Deque(int initialCapacity) {
            deque = new int[initialCapacity];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == deque.length;
        }

        public void insertIntoTail(int i) {
            if (isFull()) throw new RuntimeException("Deque is full!");
            if (tail == deque.length -1)
                tail = -1;
            deque[++tail] = i;
            capacity++;
        }

        public void insertIntoHead(int i) {
            if (isFull()) throw new RuntimeException("Deque is full!");
            if (head == 0)
                head = deque.length;
            deque[--head] = i;
            capacity++;
        }

        public int removeFromHead() {
            if (isEmpty()) throw new RuntimeException("Deque is empty!");
            int temp = deque[head++];
            head %= deque.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

        public int removeFromTail() {
            if (isEmpty()) throw new RuntimeException("Deque is empty!");
            int temp = deque[tail--];
            if (tail == -1)
                tail = deque.length - 1;
            capacity--;
            return temp;
        }
    }

    private static class PriorityQueue {
        private int[] priorityQueue;
        private int capacity;

        public PriorityQueue(int initialCapacity) {
            priorityQueue = new int[initialCapacity];
            capacity = 0;
        }

        public PriorityQueue(int... args) {
            this.capacity = args.length;
            this.priorityQueue = args;
        }

        public boolean isFull() {
            return capacity == priorityQueue.length;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public void swap(int a, int b) {
            int tmp = priorityQueue[a];
            priorityQueue[a] = priorityQueue[b];
            priorityQueue[b] = tmp;
        }

        public int getMax() {
            if (capacity == 0) throw new RuntimeException("Empty queue!");
            if (capacity == 1) return priorityQueue[0];
            int r = priorityQueue[0];
            for (int i = 0; i < capacity; i++) {
                if (priorityQueue[i] > r) {
                    r = priorityQueue[i];
                    swap(i, capacity - 1);
                }
            }
            return r;
        }

        public void insert(int i) {
            if (isFull()) throw new RuntimeException("Queue is full!");
            priorityQueue[capacity++] = i;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty!");
            int r = getMax();
            capacity--;
            return r;
        }

        @Override
        public String toString() {
            if (priorityQueue == null)
                return "null";
            int iMax = capacity - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(priorityQueue[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(stringRevers("Моя твоя не понимай!"));
        PriorityQueue priorityQueue = new PriorityQueue(123, 523, 13, -4, 2352, 9842, 1, 43, 9784);
        System.out.println(priorityQueue.remove());
        System.out.println(priorityQueue);
//        System.out.println(checkBrackets("<> () [] {} {[() <>]}"));
        //Deque
        //Priority Queue
    }
}
// ну каких то совсем уж очевидных недостатков я не нашёл, разве что было бы логично дек и приоритетную очередь
// наследовать от простой, наверное.
