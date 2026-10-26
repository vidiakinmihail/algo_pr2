package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task2 {

    public static void main(String[] args) {
        double[] arr = {0.15, 0.75, 0.40, 0.05};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(double[] arr) {
        if (arr.length == 0) return;

        int s = arr.length;
        List<List<Double>> buckets = new ArrayList<>(s); // список списков
        for (int i = 0; i < s; i++) {
            buckets.add(new ArrayList<>()); // создаём внешний список, резервируя место под s коробок
        }
        // После цикла buckets выглядит так: [ [], [], [], [] ]
        for (double val : arr) {
            int index = Math.min(s - 1, (int) (val * s)); // формула номера коробки
            buckets.get(index).add(val);
        }

        int index = 0;
        for (List<Double> bucket : buckets) {
            bubbleSort(bucket);
            for (double val : bucket) {
                arr[index++] = val;
            }
        }
    }

    private static void bubbleSort(List<Double> bucket) {
        for (int i = 0; i < bucket.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < bucket.size() - 1 - i; j++) {
                if (bucket.get(j) > bucket.get(j + 1)) {
                    double temp = bucket.get(j);
                    bucket.set(j, bucket.get(j + 1));
                    bucket.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}