import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import ex2.EquationSolver;

/**
 * Клас Main для запуску програми та управління користувацьким введенням.
 * <p>
 * Цей клас забезпечує текстове меню для взаємодії з користувачем,
 * дозволяючи виконувати різні операції, такі як перегляд результату,
 * генерація випадкового значення, збереження та відновлення даних.
 */
public class Main {
    /**
     * Об'єкт EquationSolver для виконання обчислень та роботи з результатами.
     */
    private EquationSolver calc = new EquationSolver();

    /**
     * Метод menu забезпечує текстове меню для взаємодії з користувачем.
     * <p>
     * Користувач може виконувати наступні команди:
     * <ul>
     *     <li>'q' - Вихід із програми</li>
     *     <li>'v' - Перегляд результату</li>
     *     <li>'g' - Генерація випадкового значення</li>
     *     <li>'s' - Збереження результату</li>
     *     <li>'r' - Відновлення результату</li>
     * </ul>
     */
    private void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter command: 'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore");
            try {
                s = in.readLine();
            } catch (IOException e) {
                System.out.println("Error: " + e);
                System.exit(0);
            }

            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'v':
                    calc.displayResult();
                    break;
                case 'g':
                    calc.initialize(Math.random() * 100); // Генерація випадкового значення
                    calc.displayResult();
                    break;
                case 's':
                    try {
                        calc.saveResult();
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;
                case 'r':
                    try {
                        calc.restoreResult();
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;
                default:
                    System.out.print("Wrong command. ");
            }
        } while (s.charAt(0) != 'q');
    }

    /**
     * Точка входу в програму.
     * <p>
     * Створює об'єкт Main та викликає метод {@link #menu()} для запуску програми.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}
