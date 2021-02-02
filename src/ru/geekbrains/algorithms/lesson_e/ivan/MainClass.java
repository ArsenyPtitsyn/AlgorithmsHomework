package ru.geekbrains.algorithms.lesson_e.ivan;

public class MainClass {
    private static final int[][] board = new int[8][8];
    private static void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%2d", board[i][j]);
            }
            System.out.println();
        }
    }
    private static boolean checkQueen(int x, int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0)
                    if (!(i == x && j == y)) {
                        if (i - x == 0 || j - y == 0) return false;
                        if (Math.abs(i - x) == Math.abs(j - y)) return false;
                    }
            }
        }
        return true;
    }
    private static boolean checkBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0)
                    if (!checkQueen(i, j))
                        return false;
            }
        }
        return true;
    }
    private static boolean queens(int n) {
        if (!checkBoard()) return false;
        if (n == 9) return true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = n;
                    if (queens(n + 1))
                        return true;
                    board[i][j] = 0;
                }
            }
        }
        return false;
    }
    private static int routes(int x, int y) {
        if (x == 0 || y == 0) {
            return 1;
        } else {
            return routes(x - 1, y) + routes(x, y - 1);
        }
    }
    private static int poweRec(int a, int b) {
        return (b == 0) ? 1 : poweRec(a, b - 1) * a;
    }
    public static void main(String[] args) {
        System.out.println(poweRec(2, 10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%6d", routes(i, j));
            }
            System.out.println();
        }

        queens(1);
        printBoard();
    }
}
