package ru.geekbrains.algorithms.lesson_a.ivan;

public class homework1 {
    private static int power(int base, int sign) {
        int result = 1;
        for (int i = 0; i < sign; i++)
            result *= base;

        return result;
    }

    private static int pe(int base, int sign) {
        int result = 1;
        while (sign > 0) {
            if (sign % 2 == 0) {
                sign /= 2;
                base *= base;
            } else {
                sign -= 1;
                result *= base;
            }
        }
        return result;
    }

    private static int arrMin(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < result)
                result = arr[i];

        return result;
    }

    private static float arrMean(int[] arr) {
        float result = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++)
            result += arr[i];

        return result / size;
    }

    public static void main(String[] args) {
        System.out.println(power(2, 9));
        System.out.println(pe(2, 10));

        int[] array = {5, 1, 4, -2, 0, 3};
        System.out.println(arrMin(array));
        System.out.println(arrMean(array));
    }
}



