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

    public static void main(String[] args) {
        System.out.println(recPow(2, 10));
    }
}
