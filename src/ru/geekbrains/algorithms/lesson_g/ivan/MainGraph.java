package ru.geekbrains.algorithms.lesson_g.ivan;

import ru.geekbrains.algorithms.lesson_c.ivan.Queue;
import ru.geekbrains.algorithms.lesson_c.ivan.Stack;

import java.io.IOException;
import java.util.Arrays;

public class MainGraph {
    public static void main(String[] args) {
        Graph g = new Graph(32);
        g.addVertex('a');
        g.addVertex('b');
        g.addVertex('c');
        g.addVertex('d');
        g.addVertex('e');
        g.addVertex('f');
        g.addVertex('g');
        g.addVertex('h');
        g.addVertex('i');
        g.addVertex('j');
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(3, 0);
        g.addEdge(2, 5);
        g.addEdge(5, 6);
        g.addEdge(3, 7);
        g.addEdge(6, 8);
        g.addEdge(6, 9);
        g.addEdge(7, 9);
        try {
            g.depthTraverse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("--------------");
        g.widthTravers();
        System.out.println();
        System.out.println("--------------");

//        Stack shortWay = g.shortWay('a', 'g');
//        Stack shortWay = g.shortWay('a', 'j');
        Stack shortWay = g.shortWay('a', 'i');
        if (shortWay != null)
            while (!shortWay.isEmpty()) {
                System.out.print((char) shortWay.pop());
                System.out.print((shortWay.isEmpty() ? "" : " -> "));
            }

        // why can't we simply print a queue when we got to final vertex
        System.out.println();
        Queue q = g.widthTraversPath('a', 'i');
        System.out.println(Arrays.toString(q.getQueue()));
    }
}
