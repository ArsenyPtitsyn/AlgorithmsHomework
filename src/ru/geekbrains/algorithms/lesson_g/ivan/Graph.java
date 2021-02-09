package ru.geekbrains.algorithms.lesson_g.ivan;

import ru.geekbrains.algorithms.lesson_c.ivan.Queue;
import ru.geekbrains.algorithms.lesson_c.ivan.Stack;

import java.io.IOException;

public class Graph {
    class Vertex {
        char label;
        Vertex parent;
        boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            wasVisited = false;
        }

        @Override
        public String toString() {
            return "V: " + label;
        }
    }

    private final int MAX_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;

    public Graph(int maxVertices) {
        this.MAX_VERTICES = maxVertices;
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        this.size = 0;
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void showVertex(int vertex) {
        System.out.println(vertices[vertex] + "-> ");
    }

    private int getUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] == 1 && !vertices[i].wasVisited)
                return i;
        }
        return -1;
    }

    private void resetFlags() {
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void depthTraverse() throws IOException {
        Stack stack = new Stack(MAX_VERTICES);
        vertices[0].wasVisited = true;
        showVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getUnvisitedVertex(stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertices[v].wasVisited = true;
                showVertex(v);
                stack.push(v);
            }
        }
        resetFlags();
    }

    public void widthTravers() {
        Queue queue = new Queue(MAX_VERTICES);
        vertices[0].wasVisited = true;
        showVertex(0);
        queue.insert(0);
        while (!queue.isEmpty()) {
            int vCurr = queue.remove();
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurr)) != -1) {
                vertices[vNext].wasVisited = true;
                showVertex(vNext);
                queue.insert(vNext);

            }
        }
        resetFlags();
    }

    public Queue widthTraversPath(char from, char to) {
        int start = getIndex(from);
        int stop = getIndex(to);

        Queue queue = new Queue(MAX_VERTICES);
        vertices[start].wasVisited = true;
        queue.insert(start);
        boolean done = false;
        while (!queue.isEmpty()) {
            int v1 = queue.remove();
            int v2;
            while ((v2 = getUnvisitedVertex(v1)) != -1) {
                vertices[v2].wasVisited = true;

                if (v2 == stop) {
                    done = true;
                    break;
                }
                queue.insert(v2);
            }
        }
        resetFlags();
        if (done)
            return queue;
        else
            return null;
    }

    private int getIndex(char c) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].label == c)
                return i;
        }
        return -1;
    }

    Stack shortWay(char from, char to) {            //граф не взвешен
        Stack result = new Stack(MAX_VERTICES);
        Queue queue = new Queue(MAX_VERTICES);

        int start = getIndex(from);
        int stop = getIndex(to);
        if (start == -1 || stop == -1 || start == stop)
            return null;

        vertices[start].wasVisited = true;
        queue.insert(start);
        while (!queue.isEmpty()) {                  // ищем узел, помечаем родителей
            int vCur = queue.remove();
            int vNxt;
            while ((vNxt = getUnvisitedVertex(vCur)) != -1) {
                vertices[vNxt].parent = vertices[vCur];
                vertices[vNxt].wasVisited = true;
                if (vNxt == stop) break;
                queue.insert(vNxt);
            }
            if (vNxt == stop) break;
        }
        if (!vertices[stop].wasVisited) return null;

        result.push(vertices[stop].label);
        int current = stop;
        while (vertices[current].parent != null)        // идём обратно к старту по родителям
            for (int i = 0; i < vertices.length; i++)
                if(vertices[current].parent == vertices[i]) {
                    result.push(vertices[i].label);
                    current = i;
                    break;
                }

        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
            vertices[i].parent = null;
        }
        return result;
    }
}
