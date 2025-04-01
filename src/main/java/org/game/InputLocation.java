package org.game;

import java.io.IOException;
import java.util.Scanner;

public class InputLocation {
    private static final Scanner scanner = new Scanner(System.in);

    private static String inputLocation() throws IOException, InterruptedException {
        System.out.println("Для ввода координат необходимо ввести первое число (от 0 до 99) нажать Enter и ввести второе число (от 0 до 99).");
        System.out.println("Введите первое число (от 0 до 99):");
        String firstLocation = inputLoc();
        System.out.print(firstLocation + ": "); // Выводим первое число и двоеточие
        System.out.println("Введите второе число (от 0 до 99):");
        String secondLocation = inputLoc();
        return firstLocation + ":" + secondLocation; // Возвращаем строку с координатами

    }

    private static String inputLoc() {
        int introducedCoordinate;
        while (true) {
            String input = scanner.nextLine(); // Читаем число
            if (isValidLocation(input)) {
                introducedCoordinate = Integer.parseInt(input);
                return String.valueOf(introducedCoordinate);
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите число от 0 до 99:");
            }
        }
    }

    // Метод для проверки корректности координаты
    private static boolean isValidLocation(String part) {
        if (part.length() > 2) return false; // Максимум 2 цифры
        try {
            int value = Integer.parseInt(part);
            return value >= 0 && value <= 99; // Проверяем диапазон от 0 до 99
        } catch (NumberFormatException e) {
            return false; // Если не число, возвращаем false
        }
    }

    public static void main(String[] args) throws Exception {
        String result = inputLocation();
        System.out.println("\nВведенные координаты: " + result);
    }

}
