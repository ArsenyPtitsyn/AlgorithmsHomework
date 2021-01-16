package ru.geekbrains.algorithms.lesson_a;

public class homework1 {
    public static void main(String[] args) {
        System.out.println(pow(2,7));

        float[] arr = {321.5f, 352.7f, 1.4f, 329.5f, 2.6f, 0.3f, -3.7f, -24.9f};
        System.out.println(min(arr));

        System.out.println(arithmeticMean(arr));
    }

    // 1.1. Возведение в степень *используя чётность степени*
    private static float pow(float number, int indicator) {
        float result = 1;
        if (indicator % 2 == 0) {
            for (int i = 0; i < indicator / 2; i++) {
                result *= number;
            }
            result *= result;
        } else {
            for (int i = 0; i < (indicator - 1) / 2; i++) {
                result *= number;
            }
            result *= result * number;
        }
        return result;
    } // 2.1. Сложность линейная O(indicator).
    // Формулами не пользовался. При увеличении степени на 2 количество операций увеличивается на 1.
    // Линейная зависимость.

    // 1.2. Поиск минимального элемента в массиве
    private static float min(float[] a) {
        float result = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < result)
                result = a[i];
        }
        return result;
    } // Аналогично.

    // 1.3. Нахождение среднего арифметического массива
    private static float arithmeticMean(float[] a) {
        float result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result / a.length;
    } // Аналогично.
}
