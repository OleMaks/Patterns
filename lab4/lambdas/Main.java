package lab4.lambdas;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5, 4.5);
        List<String> stringList = Arrays.asList("Banana", "Apple", "Orange", "Kiwi");
        List<String> mixedStringList = Arrays.asList("", "  ", "First Valid", "Second");
        List<String> capitalStrings = Arrays.asList("Terraria", "Minecraft", "Doom");
        String sentence = "Це речення має рівно шість слів тут";

        System.out.println("1. Непарні числа: " + filterOddNumbers(intList));

        System.out.println("2. Середнє значення: " + findAverage(doubleList));

        System.out.println("3. Алфавітне сортування: " + sortStringsAlphabetically(stringList));

        System.out.println("4. Сума парних чисел: " + sumOfEvenNumbers(intList));

        int n = 5;
        System.out.println("5. Факторіал числа " + n + ": " + calculateFactorial(n));

        long[] sumAndProduct = sumAndMultiply(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("6. Сума: " + sumAndProduct[0] + ", Добуток: " + sumAndProduct[1]);

        System.out.println("7. Квадрати чисел: " + squareNumbers(Arrays.asList(1, 2, 3, 4)));

        System.out.println("8. Сортування за довжиною: " + sortStringsByLength(stringList));

        System.out.println("9. Кількість слів у реченні: " + countWords(sentence));

        System.out.println("10. Перший непорожній рядок: " + findFirstNonEmpty(mixedStringList));

        System.out.println("11. Всі з великої літери? " + areAllStartWithCapital(capitalStrings));

        System.out.println("12. Друге за величиною число: " + findSecondLargest(intList));

        System.out.println("13. Найбільше парне число: " + findLargestEven(intList));
    }

    // 1 Фільтрація непарних чисел
    public static List<Integer> filterOddNumbers(List<Integer> list) {
        return list.stream()
                .filter(num -> num % 2 != 0)
                .collect(Collectors.toList());
    }

    // 2 Середнє значення
    public static double findAverage(List<Double> list) {
        return list.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    // 3 Сортування в алфавітному порядку
    public static List<String> sortStringsAlphabetically(List<String> list) {
        return list.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    // 4 Сума парних чисел
    public static int sumOfEvenNumbers(List<Integer> list) {
        return list.stream()
                .filter(num -> num % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    // 5 Факторіал
    public static long calculateFactorial(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (a, b) -> a * b);
    }

    // 6 Сума та множення
    public static long[] sumAndMultiply(List<Integer> list) {
        long sum = list.stream().mapToLong(Integer::longValue).sum();
        long product = list.stream().mapToLong(Integer::longValue).reduce(1, (a, b) -> a * b);
        return new long[]{sum, product};
    }

    // 7 Квадрат кожного числа
    public static List<Integer> squareNumbers(List<Integer> list) {
        return list.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
    }

    // 8 Сортування за довжиною
    public static List<String> sortStringsByLength(List<String> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    // 9 Підрахунок слів
    public static long countWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) return 0;
        return Arrays.stream(sentence.trim().split("\\s+"))
                .count();
    }

    // 10 Знайти перший непорожній рядок
    public static String findFirstNonEmpty(List<String> list) {
        return list.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .findFirst()
                .orElse("Всі рядки порожні");
    }

    // 11 Перевірка на велику літеру
    public static boolean areAllStartWithCapital(List<String> list) {
        return list.stream()
                .allMatch(s -> s != null && !s.isEmpty() && Character.isUpperCase(s.charAt(0)));
    }

    // 12 Друге за величиною число
    public static Integer findSecondLargest(List<Integer> list) {
        return list.stream()
                .distinct() 
                .sorted(Comparator.reverseOrder()) 
                .skip(1)
                .findFirst()
                .orElse(null);
    }

    // 13. Найбільше парне
    public static Integer findLargestEven(List<Integer> list) {
        return list.stream()
                .filter(num -> num % 2 == 0)
                .max(Integer::compareTo)
                .orElse(null);
    }
}