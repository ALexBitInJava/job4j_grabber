package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Sorting {
    private RandomArray array;
    private static String ln = System.lineSeparator();
    private static String menu = "1 - Создание массива" + ln
            + "2 - Сортировка пузырьком;" + ln
            + "3 - Сортировка вставками;" + ln
            + "4 - Сортировка слиянием;" + ln
            + "5 - Выход.";

    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        Scanner scanner = new Scanner(System.in);
        System.out.println("---Сортировка---");
        System.out.println(menu);
        boolean rsl = true;
        while (rsl) {
            System.out.println("---Введите цифру от 1 до 5---");

            int number = Integer.parseInt(scanner.nextLine());
                if (number == 1) {
                    RandomArray randomArray = new RandomArray(new Random());
                    randomArray.insert(250000);
                    sorting.array = randomArray;
                }
                if (number == 2) {
                    BubbleSort bubbleSort = new BubbleSort();
                    bubbleSort.sort(sorting.array);
                }
                if (number == 3) {
                    InsertSort insertSort = new InsertSort();
                    insertSort.sort(sorting.array);
                }
                if (number == 4) {
                    MergeSort mergeSort = new MergeSort();
                    mergeSort.sort(sorting.array);
                }
                if (number < 0 || number >= 5) {
                    System.out.println("Выход из меню");
                    rsl = false;
                }
        }
    }
}