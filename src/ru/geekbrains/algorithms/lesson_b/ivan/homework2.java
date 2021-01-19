package ru.geekbrains.algorithms.lesson_b.ivan;

import java.util.Arrays;

public class homework2 {
    private static class Array {
        private int[] arr;
        private int size;
        private boolean isSorted;

        private Array() {
            isSorted = false;
        }

        public Array(int size) {
            this();
            this.arr = new int[size];
        }

        public Array(int... args) {
            this();
            this.size = args.length;
            this.arr = args;
        }

        public Array(boolean isSorted, int... args) {
            this(args);
            this.isSorted = isSorted;
        }

        public int get(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            return arr[index];
        }

        public void set(int index, int value) {
            arr[index] = value;
            isSorted = false;
        }

        public boolean delete() { // last
            if (size == 0) return false;
            size--;
            return true;
        }

        public boolean delete(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);

            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            size--;
            return true;
        }

        public boolean deleteAll(int value) {
            boolean success = false;
            for (int i = 0; i < size; i++) {
                if (arr[i] == value) {
                   delete(i--);
                    success = true;
                }
            }
            return success;
        }

        public void deleteAll() {
            size = 0;
        }

        public void append(int value) {
            if (size >= arr.length - 1) {
                int[] temp = arr;
                arr = new int[size * 2];
                System.arraycopy(temp, 0, arr, 0, size);
            }
            arr[size++] = value;
            isSorted = false;
        }

        public void insert(int index, int value) {
            int[] temp = arr;
            if (size >= arr.length) {
                arr = new int[size * 2];
                System.arraycopy(temp, 0, arr, 0, index);
            }
            System.arraycopy(temp, index, arr, index + 1, size - index);
            arr[index] = value;
            size++;
            isSorted = false;
        }

        public boolean isInArray(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value) {
                    return true;
                }
            }
            return false;
        }

        public int hasValue(int value) {
            if (!isSorted)
                throw new RuntimeException("Trying to search in unsorted array");

            int l = 0;
            int r = size;
            int m;
            while (l < r) {
                m = (l + r) >> 1;
                if (value == arr[m]) {
                    return m;
                } else {
                    if (value < arr[m]) {
                        r = m;
                    } else {
                        l = m + 1;
                    }
                }
            }

            return -1;
        }

        private void swap(int a, int b) {
            int tmp = this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }

        public int sortBubble() {
            int c = 0;
            for (int out = size - 1; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    c++;
                    if (this.arr[in] > arr[in + 1]) {
                        swap(in, in + 1);
                    }
                }
            }
            isSorted = true;
            return c;
        }

        public int sortDoubleBubble() {
            int c = 0;
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - 1 - i; j++) {
                    c++;
                    if (arr[j] > arr[j + 1]) {
                        swap(j, j + 1);
                        flag = true;
                    }
                }
                if (!flag) break;
            }
            isSorted = true;
            return c;
        }

        public int sortSelect() {
            int c = 0;
            for (int i = 0; i < size; i++) {
                int flag = i;
                for (int j = i + 1; j < size; j++) {
                    c++;
                    if (arr[j] < arr[flag])
                        flag = j;
                }
                swap(i, flag);
            }
            isSorted = true;
            return c;
        }

        public int sortInsert() {
            int c = 0;
            for (int out = 1; out < size; out++) {
                int temp = arr[out];
                int in = out;
                while (in > 0 && arr[in - 1] >= temp) {
                    c++;
                    arr[in] = arr[in - 1];
                    in--;
                }
                arr[in] = temp;
            }
            isSorted = true;
            return c;
        }

        int getMax() {
            if (size == 0) throw new RuntimeException("Empty array");
            if (size == 1) return arr[0];
            int r = arr[0];
            for (int i = 0; i < size; i++) {
                if (arr[i] > r)
                    r = arr[i];
            }
            return r;
        }

        int getMin() {
            if (size == 0) throw new RuntimeException("Empty array");
            if (size == 1) return arr[0];
            int r = arr[0];
            for (int i = 0; i < size; i++) {
                if (arr[i] < r)
                    r = arr[i];
            }
            return r;
        }

        public int pigeonHoleSort() {
            int c = 0;
            int min = getMin();
            int max = getMax();
            int[] freq = new int[max - min + 1];
            for (int i = 0; i < size; i++) {
                c++;
                freq[arr[i] - min]++;
            }

            int arrIndex = 0;
            for (int i = 0; i <freq.length ; i++) {
                for (int elems = freq[i]; elems > 0; elems--) {
                    c++;
                    arr[arrIndex++] = i + min;
                }
            }
            return c;
        }

        @Override
        public String toString() {
            if (arr == null)
                return "null";
            int iMax = size - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arr[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
    }

    public static void main(String[] args) {
        Array array = new Array(9,2,3,7,4,5,6,7,1,8,0);
        System.out.println(array);
//        array.delete();
//        array.delete();
//        System.out.println(array);
//        array.delete(2);
//        System.out.println(array);
        array.sortBubble();
        System.out.println(array);
        System.out.println(array.hasValue(7));
        array.insert(11, 3);
        System.out.println(array);
//        array.deleteAll(7);
//        System.out.println(array);
//        System.out.println(array.delete());
//        array.append(3);
//        System.out.println(array);
    }

    private static void standardArrayThings() {
        int[] arr;
        int ar2[];
        ar2 = new int[5];
        arr = new int[]{1,2,3,'_',5,6,7};
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
