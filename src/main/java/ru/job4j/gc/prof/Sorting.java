package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Sorting {
    private int number;
    private RandomArray randomArray;
    private static String ln = System.lineSeparator();
    private static String menu =
             "1 - Сортировка пузырьком;" + ln
            + "2 - Сортировка вставками;" + ln
            + "3 - Сортировка слиянием;" + ln
            + "4 - Выход.";

    public static void main(String[] args) {
        Sorting sorting = new Sorting();
        System.out.println("---Автоматическое создание массива---");
        RandomArray array = new RandomArray(new Random());
        array.insert(250000);
        sorting.randomArray = array;
        System.out.println("---Сортировка---");
            System.out.println(menu);
        System.out.println("---Введите цифру от 1 до 4---");
            Scanner scanner = new Scanner(System.in);
            sorting.number = Integer.parseInt(scanner.nextLine());
            if (sorting.number == 1) {
                BubbleSort bubbleSort = new BubbleSort();
                bubbleSort.sort(sorting.randomArray);
            }
            if (sorting.number == 2) {
                InsertSort insertSort = new InsertSort();
                insertSort.sort(sorting.randomArray);
            }
            if (sorting.number == 3) {
                MergeSort mergeSort = new MergeSort();
                mergeSort.sort(sorting.randomArray);
            }
            if (sorting.number == 4) {
            System.out.println("Выход из меню");

        }
    }
}
