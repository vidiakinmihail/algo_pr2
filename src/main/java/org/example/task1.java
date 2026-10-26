package org.example;

import java.util.Arrays;

/**
 * Поразрядная сортировка (radix sort, LSD) номеров билетов.
 *
 * Номера неотрицательные, до 99 999 999 (8 разрядов).
 * На каждом проходе — устойчивая сортировка подсчётом по одной цифре (0..9).
 *
 * Время: O(d * n), где d — число разрядов (для 99 999 999 d = 8), то есть фактически O(n).
 * Память: O(n + 10) — выходной массив плюс счётчик на 10 цифр.
 */
public class task1 {

    public static void main(String[] args) {
        int[] first = {500, 12, 99999999, 42};
        System.out.println(Arrays.toString(first));
        sort(first);
        System.out.println(Arrays.toString(first)); // [12, 42, 500, 99999999]

        int[] second = {101, 10, 1};
        System.out.println(Arrays.toString(second));
        sort(second);
        System.out.println(Arrays.toString(second)); // [1, 10, 101]
    }

    public static void sort(int[] arr) {
        if (arr.length == 0) return;

        int max = arr[0];
        for (int val : arr) {
            if (val < 0) return;
            max = Math.max(val, max);
        }

        for (int place = 1; max / place > 0; place *= 10) {
            countingSortByDigit(arr, place);
        }

    }

    private static void countingSortByDigit(int[] arr, int place) {

        int[] count = new int[10];
        for (int val : arr) {
            count[(val / place) % 10]++;
        }

        for (int digit = 1; digit < 10; digit++) {
            count[digit] += count[digit - 1];
        }

        int[] result = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / place) % 10;
            result[--count[digit]] = arr[i];
        }

        System.arraycopy(
                result,
                0,
                arr,
                0,
                result.length
        );
    }

}