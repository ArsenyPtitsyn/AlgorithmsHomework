package ru.geekbrains.algorithms.lesson_d.ivan;

import java.util.Objects;

public class DoubleRelatedList<T> {
    private class Iter implements MyIterator<T> {
        Node<T> current;

        @Override
        public void reset() {
            current = head;
        }

        @Override
        public boolean atEnd() {
            if (!listExists()) throw new RuntimeException("iterator is null");
            return current.next == null;
        }

        @Override
        public boolean atBegin() {
            if (!listExists()) throw new RuntimeException("iterator is null");
            return current.prev == null;
        }

        private boolean listExists() {
            return iterator != null;
        }

        @Override
        public T deleteCurrent() {
            if (!listExists()) throw new RuntimeException("iterator is null");
            T temp = current.c;
            if (atBegin() && atEnd()) {
                head = null;
                tail = null;
                reset();
            } else if (atBegin()) {
                head = current.next;
                head.prev = null;
                reset();
            } else if (atEnd()) {
                tail = current.prev;
                tail.next = null;
                current = current.next;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
            }
            return temp;
        }

        @Override
        public void insertAfter(T c) {
            if (!listExists()) throw new RuntimeException("iterator is null");
            Node<T> temp = new Node<>(c);
            if (!atEnd()) {
                temp.next = current.next;
                current.next.prev = temp;
            } else {
                tail = temp;
            }
            current.next = temp;
            temp.prev = current;
        }

        @Override
        public void insertBefore(T c) {
            if (!listExists()) throw new RuntimeException("iterator is null");
            Node<T> temp = new Node<>(c);
            if (!atBegin()) {
                temp.prev = current.prev;
                current.prev.next = temp;
            } else {
                head = temp;
            }
            current.prev = temp;
            temp.next = current;
        }

        @Override
        public T getCurrent() {
            return current.c;
        }

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Node<T> next() {
            if (hasNext()) return current.next;
            else return null;
        }
    }

    private class Node<T> {
        T c;
        Node<T> next;
        Node<T> prev;
        public Node(T c) {
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(c, node.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }

        @Override
        public String toString() {
            return c.toString();
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private MyIterator<T> iterator;

    public DoubleRelatedList() {
        head = null;
        tail = null;
        iterator = new Iter();
        iterator.reset();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(Cat c) {
        Node n = new Node<>(c);
        n.next = head;
        if (head == null) {
            tail = n;
        } else {
            head.prev = n;
        }
        head = n;
        iterator.reset();
    }

    public T pop() {
        if (isEmpty()) return null;
        T c = tail.c;
        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
            iterator.reset();
        } else {
            tail = null;
            head = null;
            iterator = null;
        }
        return c;
    }

    public boolean contains(T c) {
        Node<T> n = new Node<>(c);
        Node<T> current = head;
        while (!current.equals(n)) {
            if (current.next == null) return false;
            else current = current.next;
        }
        return true;
    }

    public T delete(T c) {
        Node<T> n = new Node<>(c);
        Node<T> current = head;
        Node<T> previous = head;

        while (!current.equals(n)) {
            if (current.next == null) return null;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head && current == tail) {
            head = null;
            tail = null;
            iterator = null;
        } else if (current == head) {
            head.next.prev = null;
            head = head.next;
        } else if (current == tail) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            previous.next = current.next;
            current.next.prev = previous;
        }
        return current.c;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        Node current = head;
        StringBuilder sb = new StringBuilder("[ ");
        while (current != null) {
            sb.append(current);
            current = current.next;
            sb.append((current == null) ? " ]" : ", ");
        }
        return sb.toString();
    }

    public MyIterator<T> getIterator() {
        return iterator;
    }
}
