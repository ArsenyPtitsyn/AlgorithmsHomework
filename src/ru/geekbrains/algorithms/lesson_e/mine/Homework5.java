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

    public static void main(String[] args) {
        System.out.println(recPow(2, 10));
        System.out.println(king(2, 3));
    }
}
