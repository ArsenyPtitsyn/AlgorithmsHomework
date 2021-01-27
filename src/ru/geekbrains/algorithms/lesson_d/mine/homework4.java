package ru.geekbrains.algorithms.lesson_d.mine;

import java.util.Iterator;
import java.util.Objects;

public class homework4 {
    // class iterator +-
    // reset() +-
    // next(), prev(for dll) +-
    // getCurrent() +-
    // atEnd() +-
    // insertBefore() --
    // insertAfter() --
    // deleteCurrent() --
    private static class Cat {
        int age;
        String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat(a=%d,n=%s)", age, name);
        }
    }
    private static class SingleLinkedList {
        private class Iterator {
            Node current;
            public Iterator() {
                this.current = head;
            }
            public void reset() {
                current = head;
            }
            public void next() {
                if (isEmpty()) {
                    current = null;
                    return;
                }
                current = current.next;
            }
            public Cat getCurrent() {
                if (isEmpty()) return null;
                return current.c;
            }
            public void atEnd() {
                if (isEmpty()) current = null;
                else {
                    while (current.next != null)
                        next();
                }
            }
        }
        private class Node {
            Cat c;
            Node next;
            public Node(Cat c) {
                this.c = c;
            }
            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }
        private Node head;
        public SingleLinkedList() {
            this.head = null;
        }
        public boolean isEmpty() {
            return head == null;
        }
        public void push(Cat c) {
            Node n = new Node(c);
            n.next = head;
            head = n;
        }
        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }
        public boolean contains(Cat c) {
            Node n = new Node(c);
            Node current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }
        public void delete(Cat c) {
            Node n = new Node(c);
            Node current = head;
            Node previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }

            // return current.c

        }
    }
    public static void main(String[] args) {

    }
}
