package ex2;

import java.util.Random;

/**
 * Клас Individually для обчислення найдовшої послідовності одиниць
 * у двійковому представленні числа, отриманого з математичних обчислень.
 */
public class Individually {

    /**
     * Обчислює найдовшу послідовність одиниць у двійковому представленні числа.
     * <p>
     * Формула для обчислення:
     * <ul>
     *     <li>n = округлене значення {@code 10 * cos(alpha)}</li>
     *     <li>S = {@code n^2 + n^3}</li>
     * </ul>
     * Після цього число {@code S} перетворюється у двійковий формат, і визначається
     * найдовша послідовність одиниць.
     *
     * @param alpha Кут у радіанах.
     * @return Довжина найдовшої послідовності одиниць.
     */
    public static int longestOnesSequence(double alpha) {
        int n = (int) Math.round(10 * Math.cos(alpha)); // Округлення до найближчого цілого
        int S = n * n + n * n * n; // n^2 + n^3

        String binary = Integer.toBinaryString(S); // Перетворення у двійковий формат
        String[] onesGroups = binary.split("0"); // Розділення за нулями
        int maxLength = 0;

        for (String group : onesGroups) {
            if (group.length() > maxLength) {
                maxLength = group.length();
            }
        }

        System.out.println("Angle  a (in degrees): " + Math.toDegrees(alpha));
        System.out.println("Number  n: " + n);
        System.out.println("Number  S: " + S);
        System.out.println("Binary representation: " + binary);
        System.out.println("The longest sequence 1: " + maxLength);
        
        return maxLength;
    }

    /**
     * Точка входу в програму.
     * <p>
     * Генерує випадковий кут у межах [0, 360] градусів, обчислює його у радіанах
     * та викликає метод {@link #longestOnesSequence(double)} для обчислення.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Random random = new Random();
        double alpha = Math.toRadians(random.nextDouble() * 360); // Випадковий кут у межах [0, 360] градусів
        longestOnesSequence(alpha);
    }
}