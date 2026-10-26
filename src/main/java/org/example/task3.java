package org.example;

import java.util.Arrays;
import java.util.Random;

/**
 * Сортировка результатов броска кубика (значения 1..6) подсчётом (counting sort).
 *
 * Диапазон значений маленький и известный заранее (1..6), поэтому
 * считаем, сколько раз встречается каждое значение, и восстанавливаем
 * отсортированный массив. Заодно получаем количество повторов.
 *
 * Время: O(n + k), где k = 6 — диапазон значений, то есть фактически O(n).
 * Память: O(k) = O(1).
 */
public class task3 {

    public static void main(String[] args) {
        int[] first = {6, 1, 6, 3, 2};
        System.out.println(Arrays.toString(first));
        sort(first);
        System.out.println(Arrays.toString(first)); // [1, 2, 3, 6, 6]

        int[] second = {4, 4, 2, 5};
        System.out.println(Arrays.toString(second));
        sort(second);
        System.out.println(Arrays.toString(second)); // [2, 4, 4, 5]
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int min = arr[0];
        int max = arr[0];
        for (int val : arr) {
            min = Math.min(min, val);
            max = Math.max(max, val);
        }

        int range = max - min + 1; // кол-во значений, которые могут встретится
        int[] count = new int[range];

        for (int val : arr) {  // подсчет частот
            int offset = val - min;
            count[offset]++;
        }

        int index = 0;
        for (int offset = 0; offset < count.length; offset++) {
            for (int repeat = 0; repeat < count[offset]; repeat++) {
                arr[index++] = offset + min;
            }
        }
    }

}