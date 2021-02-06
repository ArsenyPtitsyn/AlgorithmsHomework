package ru.geekbrains.algorithms.lesson_f.ivan;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Garden {
    private static class Cat {
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    Objects.equals(name, cat.name);
        }

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("C(%s, %d)", name, age);
        }
    }
    private static class Tree {
        private static class TreeNode implements Comparable {
            private Cat c;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(Cat c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", c);
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof TreeNode))
                    throw new ClassCastException("Not a cat!");
                return c.age - ((TreeNode) o).c.age;
            }
        }
        TreeNode root;

        public void insert(Cat c) {
            TreeNode node = new TreeNode(c);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
//                    int comparsion = node.compareTo(current);
//                    if (comparsion == 0) return;
//                    current = (comparsion < 0) ? current.left : current.right;
//                    if (current == null) {
//                        if (comparsion < 0) parent.left = node;
//                        else parent.right = node;
//                        return;
//                    }
                    if (c.age < current.c.age) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            return;
                        }
                    } else if (c.age > current.c.age) {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        public Cat find(int age) {
            TreeNode current = root;
            while (current.c.age != age) {
                current = (age < current.c.age) ? current.left : current.right;
                if (current == null) return null;
            }
            return current.c;
        }
        public void preOrderTraverse(TreeNode currentNode) {
            if (currentNode != null) {
                System.out.println(currentNode);
                preOrderTraverse(currentNode.left);
                preOrderTraverse(currentNode.right);
            }
        }
        // LeftRootRight
        // RightLeftRoot
        public void displayTree() {
            preOrderTraverse(root);
        }
        public Cat delete(int age)  {
            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.c.age != age) {
                parent = current;
                if (age < current.c.age) {
                    current = current.left;
                    isLeftChild = true;
                } else  {
                    current = current.right;
                    isLeftChild = false;
                }
                if (current == null) {
                    return null;
                }
            }
            //leaf
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            }
            else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return current.c;
        }
        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

        public Tree(List<Integer> sampleData) {
            for (int i = 0; i < sampleData.size(); i++) {
                insert(new Cat(sampleData.get(i), "Cat" + sampleData.get(i)));
            }
        }
        public boolean isBalanced(boolean precision) {
            return Math.abs(countDepth(root.left) - countDepth(root.right))
                    <= ((precision) ? 0 : 1);
        }
        private int countDepth(TreeNode node) {
            if (node == null) return 0;

            int left = 0;
            int right = 0;

            if (node.left != null)
                left = countDepth(node.left);

            if (node.right != null)
                right = countDepth(node.right);

            return 1 + Math.max(left, right);
        }

    }
    private static void uniqueRandom(ArrayList<Integer> a, int amount) {
        SecureRandom sr = new SecureRandom();
        while (a.size() < amount) {
            int num = sr.nextInt();
            if (!a.contains(num))
                a.add(num);
        }
    }

    public static void main(String[] args) {
        final int TREES = 50;
        int balanced = 0;
        for (int i = 0; i < TREES; i++) {
            ArrayList<Integer> a = new ArrayList<>();
            uniqueRandom(a, 10000);
            Tree t = new Tree(a);
            balanced += (t.isBalanced(false)) ? 1 : 0;
        }
        System.out.println(balanced * 100f / TREES + "%");
    }
}