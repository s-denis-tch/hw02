package com.sokolov;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int size = 5_000_000;

        System.out.println("Strings (40 bytes): ");
        printSize("Empty string size (100 000 elements): ", () -> new String(), 100_000);
        printSize("Empty string size: ", () -> new String(), size);
        printSize("String size: ", () -> new String("Raiders"), size);

        System.out.println("Objects (8 bytes): ");
        printSize("Object size (100 000 elements): ", () -> new Object(), 100_000);
        printSize("Object size: ", () -> new Object(), size);

        System.out.println("Arrays (12 bytes): ");
        printSize("1. Array of int (100 000 elements): ", () -> new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 100_000);
        printSize("2. Array of int: ", () -> new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, size);
        printSize("3. Array of long: ", () -> new long[]{0L, 1L, 2L, 3L, 4L}, size);
        printSize("4. Array of char[10]: ", () -> new char[10], size);
        printSize("5. Array of char[0]: ", () -> new char[0], size);

        System.out.println("������ ����� ������� ���������� � ���-�� �� ���������� ���������: ");
        List<Integer> x = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        x.stream().map(n -> {
            int[] arr = n <= 0 ? new int[1] : new int[n];
            for (int i = 0; i < n; i++) arr[i] = i;
            return arr;
        }).forEach(m -> {
            printSize("Array of int[" + m.length + "]: ", () -> m.clone(), size);
        });
    }

    private static <T> void printSize(String text, Supplier<T> supplier, int size) {
        StringBuilder sb = new StringBuilder().append(text).append(Standart.getSize(supplier, size));
        System.out.println(sb);
    }
}