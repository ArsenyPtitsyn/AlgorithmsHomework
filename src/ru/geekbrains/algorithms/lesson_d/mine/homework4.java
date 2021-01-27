package ru.geekbrains.algorithms.lesson_d.mine;

import java.util.Objects;

public class homework4 {
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
            public void prev() {
                Node previous = head;
                if (isEmpty() || current == head) {
                    current = null;
                }
                while (!previous.next.equals(current)) {
                    previous = previous.next;
                }
                current = previous;
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
            public void insertBefore(Cat c) {
                Node n = new Node(c);
                if (isEmpty()) {
                    head = n;
                }
                Node previous = head;
                if (current == head) {
                    n.next = head;
                    head = n;
                }
                while (!previous.next.equals(current)) {
                    previous = previous.next;
                }
                n.next = current;
                previous.next = n;
            }
            public void insertAfter(Cat c) {
                Node n = new Node(c);
                n.next = current.next;
                current.next = n;
            }
            public void deleteCurrent() {
                Node previous = head;
                if (isEmpty())
                    throw new RuntimeException("List is empty!");
                while (!previous.next.equals(current))
                    previous = previous.next;
                if (current == head) {
                    head = head.next;
                } else {
                    previous.next = current.next;
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
        Iterator i = new Iterator();
        Node head;
        public SingleLinkedList(Node head) {
            this.head = head;
        }
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
    // С наследованием не получилось! Для этого нужно больше времени! Слишком сложно! 3ч не хватило...
    // Надо распривачивать все поля, касттайпить и прочее...Прям я прошу прощения, просто!
    private static class DoubleLinkedList {
        private class IteratorDLL {
            NodeForDLL current;
            public IteratorDLL() {
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
            public void prev() {
                if (isEmpty()) {
                    current = null;
                    return;
                }
                current = current.prev;
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
            public void atBegin() {
                if (isEmpty()) current = null;
                else {
                    while (current.prev != null)
                        prev();
                }
            }
            public void insertBefore(Cat c) {
                NodeForDLL n = new NodeForDLL(c);
                if (isEmpty()) {
                    head = n;
                    tail = n;
                }
                if (current == head) {
                    n.next = head;
                    head = n;
                }
                n.next = current;
                current.prev.next = n;
                n.prev = current.prev;
                current.prev = n;
            }
            public void insertAfter(Cat c) {
                NodeForDLL n = new NodeForDLL(c);
                if (isEmpty()) {
                    head = n;
                    tail = n;
                }
                if (current == tail) {
                    n.prev = tail;
                    tail = n;
                }
                n.next = current.next;
                current.next = n;
                n.prev = current;
                current.next.prev = n;
            }
            public void deleteCurrent() {
                if (isEmpty())
                    throw new RuntimeException("List is empty!");
                if (current == head) {
                    head = head.next;
                } else if (current == tail) {
                    tail = tail.prev;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
            }
        }
        private class NodeForDLL{
            Cat c;
            NodeForDLL prev;
            NodeForDLL next;
            public NodeForDLL(Cat c) {
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
                NodeForDLL node = (NodeForDLL) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next) &&
                        Objects.equals(prev, node.prev);
            }
        }
        IteratorDLL iDLL = new IteratorDLL();
        NodeForDLL head;
        NodeForDLL tail;
        public DoubleLinkedList() {
            super();
            this.tail = null;
        }
        public boolean isEmpty() {
           return head == null && tail == null;
        }
        public void pushIntoHead(Cat c) {
            NodeForDLL n = new NodeForDLL(c);
            n.next = head;
            head = n;
            n.prev = null;
        }
        public void pushIntoTail(Cat c) {
            NodeForDLL n = new NodeForDLL(c);
            n.prev = tail.prev;
            tail = n;
            n.next = null;
        }
        public Cat popFromHead() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }
        public Cat popFromTail() {
            if (isEmpty()) return null;
            Cat temp = tail.c;
            tail = tail.prev;
            return temp;
        }
        public boolean contains(Cat c) {
            NodeForDLL n = new NodeForDLL(c);
            NodeForDLL current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }
        public void delete(Cat c) {
            NodeForDLL n = new NodeForDLL(c);
            NodeForDLL current = head;
            NodeForDLL previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
                head.prev = null;
            } else {
                previous.next = current.next;
                current.next.prev = current.prev;
            }
        }
    }
    public static void main(String[] args) {

    }
}
