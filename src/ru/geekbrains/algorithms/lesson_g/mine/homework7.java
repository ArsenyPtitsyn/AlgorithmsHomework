package ru.geekbrains.algorithms.lesson_g.mine;

import ru.geekbrains.algorithms.lesson_c.ivan.Queue;
import ru.geekbrains.algorithms.lesson_c.ivan.Stack;

public class homework7 {
    private static class Graph {
        private class Vertex {
            char label;
            boolean wasVisited;

            public Vertex(char label) {
                this.label = label;
                this.wasVisited = false;
            }

            @Override
            public String toString() {
                return String.format("V=%c", label);
            }
        }

        private final int MAX_VERTICES = 16;
        private Vertex[] vertexList;
        private int[][] adjacencyMatrix;
        private int currentSize;

        public Graph() {
            vertexList = new Vertex[MAX_VERTICES];
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            currentSize = 0;
        }
        public void addVertex(char label) {
            vertexList[currentSize++] = new Vertex(label);
        }
        private boolean contains(char c) {
            for (int i = 0; i < currentSize; i++) {
                if (vertexList[i].label == c) return true;
            }
            return false;
        }
        public void addEdge(char start, char end) {
            if (!(this.contains(start) || this.contains(end)))
                throw new RuntimeException("no such vertices in graph");
            for (int i = 0; i < currentSize; i++) {
                if (vertexList[i].label == start) {
                    for (int j = 0; j < currentSize; j++) {
                        if (vertexList[j].label == end) {
                            adjacencyMatrix[i][j] = 1; // change 1 to weight for weight
                            adjacencyMatrix[j][i] = 1; // delete this for direction
                        }
                    }
                }
            }
        }
        public void displayVertex(int v) {
            System.out.print(vertexList[v] + " ");
        }
        private int getUnvisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        !vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }
        public void depthTraverse() {
            Stack stack = new Stack(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            displayVertex(0);
            stack.push(0);
            while (!stack.isEmpty()) {
                int v = getUnvisitedVertex(stack.peek());
                if (v == -1) {
                    stack.pop();
                } else {
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    stack.push(v);
                }
            }
            resetFlags();
        }
        public void widthTraverse() {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                displayVertex(current);
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    queue.insert(next);
                }
            }
            resetFlags();
        }
        private void resetFlags() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addEdge('a', 'c');
        g.addEdge('c', 'b');
        g.addEdge('d', 'e');
        g.addEdge('a', 'e');
        g.depthTraverse();
        System.out.println();
        g.widthTraverse();
    }
}
