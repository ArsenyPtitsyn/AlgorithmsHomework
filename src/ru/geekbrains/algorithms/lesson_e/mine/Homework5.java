package ru.geekbrains.algorithms.lesson_e.mine;

public class Homework5 {
    private static int recPow(int degreeBasis, int degreeIndicator) {
        if ((degreeBasis == 0 && degreeIndicator <= 0)) throw new RuntimeException("the value is not defined");
        if (degreeIndicator < 0 && degreeBasis != 1)
            throw new RuntimeException("this method is not intended for calculating degrees with a negative indicator");
        if (degreeBasis == 1) return 1;
        if (degreeBasis != 0 && degreeIndicator == 0) return 1;
        else {
            degreeBasis *= recPow(degreeBasis, --degreeIndicator);
        }
        return degreeBasis;
    }

    private static int king(int x, int y) {
        if (x < 0 || y < 0) throw new RuntimeException("incorrect coordinates");
        if (x == 0 || y == 0) return 1;
        else {
            return king(x - 1, y) + king(x, y - 1);
        }
    }

    private static int[][] directions = {
            {1, -1}, {1, 0}, {1, 1}, {0, 1},
            {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}
    };

    private static boolean isPossible(int[][] desk, int x, int y) {
        return x >= 0 && x < desk[0].length &&
                y >= 0 && y < desk.length &&
                desk[y][x] == 0;
    }

    private static boolean isValid(int[][] desk, int x, int y) {
        return x >= 0 && x < desk[0].length && y >= 0 && y < desk.length;
    }

    private static void fillLinesOfQueen(int[][] desk, int x, int y, int numberOfQueen) {
        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < desk.length; j++) {
                if (isValid(desk, x + j * directions[i][1], y + j * directions[i][0])) {
                    desk[y + j * directions[i][0]][x + j * directions[i][1]] = numberOfQueen;
                }
            }
        }
    }

    private static boolean setQueen(int[][] desk, int x, int y, int numberOfQueen) {
        desk[y][x] = numberOfQueen;
        fillLinesOfQueen(desk, x, y, numberOfQueen);
        if (numberOfQueen > desk.length - 1) return true;

        int nextX;
        int nextY;
        for (int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk[i].length; j++) {
                if (desk[i][j] == 0) {
                    nextX = j;
                    nextY = i;
                    if (setQueen(desk, nextX, nextY, numberOfQueen + 1))
                        return true;
                }
            }

        }
        desk[y][x] = 0;
        fillLinesOfQueen(desk, x, y, 0);
        return false;
    }

    private static void printDesk(int[][] desk) {
        for (int y = 0; y < desk.length; y++) {
            for (int x = 0; x < desk[y].length; x++) {
                System.out.printf("%3d", desk[y][x]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(recPow(2, 10));
        System.out.println(king(2, 3));
        int[][] desk = new int[8][8];
        setQueen(desk, 0, 2, 1);
        printDesk(desk);
    }
}
