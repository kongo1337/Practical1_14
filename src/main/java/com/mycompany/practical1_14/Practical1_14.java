/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.practical1_14;

/**
 *
 * @author KonGo
 */
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Stream;

public class Practical1_14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа через запятую:");
        String input = scanner.nextLine();
        int[] numbers = Stream.of(input.split(","))
                              .mapToInt(Integer::parseInt)
                              .toArray();

        int[] min = {Integer.MAX_VALUE};
        int[] max = {Integer.MIN_VALUE};

        Thread minThread = new Thread(() -> {
            for (int num : numbers) {
                if (num < min[0]) {
                    min[0] = num;
                }
            }
        });

        Thread maxThread = new Thread(() -> {
            for (int num : numbers) {
                if (num > max[0]) {
                    max[0] = num;
                }
            }
        });

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Минимальное значение: " + min[0]);
        System.out.println("Максимальное значение: " + max[0]);
    }
}