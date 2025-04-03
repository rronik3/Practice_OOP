import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import ex2.EquationSolver;
import ex3.View;
import ex4.ViewableTable;
import ex5.CommandManager;
import ex5.GenerateCommand;
import ex5.SaveCommand;
import ex5.RestoreCommand;
import ex5.SolveEquationCommand;
import ex6.CommandQueue;
import ex6.MinMaxCommand;
import ex3.ViewResult;
import ex6.ExecuteConsoleCommand;

/**
 * Головний клас програми для управління користувацьким введенням і виконанням команд.
 * <p>
 * Цей клас забезпечує текстове меню для взаємодії з користувачем, дозволяючи виконувати
 * різні операції, такі як перегляд результатів, генерація випадкових значень, збереження
 * та відновлення даних, а також розв'язання квадратних рівнянь.
 * </p>
 * 
 * @author xone
 * @version 1.3
 */
public class Main {
    private View view; // Об'єкт для відображення результатів
    private EquationSolver calc = new EquationSolver(); // Об'єкт для обчислення рівнянь
    private ViewResult viewResult = new ViewResult(); // Об'єкт для відображення результатів

    /**
     * Конструктор, який приймає об'єкт {@link View} для відображення результатів.
     *
     * @param view Об'єкт для відображення результатів.
     */
    public Main(View view) {
        this.view = view;
    }

    /**
     * Дефолтний конструктор для Main.
     * <p>
     * Використовується для ініціалізації за замовчуванням, якщо об'єкт {@link View} не передано.
     * </p>
     */
    public Main() {
        // Ініціалізація за замовчуванням, якщо потрібно
    }

    /**
     * Метод menu забезпечує текстове меню для взаємодії з користувачем.
     * <p>
     * Користувач може виконувати наступні команди:
     * <ul>
     *     <li>'q' - Вихід із програми</li>
     *     <li>'v' - Перегляд результатів</li>
     *     <li>'g' - Генерація випадкових значень</li>
     *     <li>'s' - Збереження результатів</li>
     *     <li>'r' - Відновлення результатів</li>
     *     <li>'u' - Скасування останньої операції</li>
     *     <li>'e' - Розв'язання квадратного рівняння</li>
     *     <li>'i' - Введення чисел користувачем</li>
     * </ul>
     * </p>
     * <p>
     * Кожна команда обробляється через відповідний клас команди, використовуючи
     * шаблон Command. Скасування операцій реалізовано через {@link CommandManager}.
     * </p>
     */
    private void menu() {
        CommandManager commandManager = CommandManager.getInstance();
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter command: 'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore, 'u'ndo, 'e'quation, 'i'nput numbers");
            try {
                s = in.readLine();
                if (s == null || s.trim().isEmpty()) {
                    System.out.println("No command entered. Please try again.");
                    continue;
                }
            } catch (IOException e) {
                System.out.println("Error: " + e);
                System.exit(0);
            }
            switch (s.charAt(0)) {
                case 'q':
                    // Завершення програми
                    System.out.println("Exit.");
                    break;
                case 'v':
                    // Відображення результатів
                    view.viewShow();
                    break;
                case 'g':
                    // Генерація випадкових значень
                    System.out.println("Enter stepX:");
                    try {
                        String input = in.readLine();
                        if (input == null || input.trim().isEmpty()) {
                            System.out.println("No input provided. Please enter a valid number.");
                            continue;
                        }
                        double stepX = Double.parseDouble(input);
                        commandManager.executeCommand(new GenerateCommand(view, stepX));
                    } catch (IOException e) {
                        System.out.println("Error reading input: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;
                case 's':
                    // Збереження результатів
                    commandManager.executeCommand(new SaveCommand(view));
                    break;
                case 'r':
                    // Відновлення результатів
                    commandManager.executeCommand(new RestoreCommand(view));
                    break;
                case 'u':
                    // Скасування останньої операції
                    commandManager.undo();
                    break;
                case 'e':
                    // Ініціалізація даних і виконання команди
                    viewResult.init(1.0); // Ініціалізуємо дані
                    new ExecuteConsoleCommand(viewResult).execute();
                    break;
                case 'i':
                    // Розв'язання квадратного рівняння
                    System.out.println("Enter coefficients a, b, c:");
                    try {
                        String inputA = in.readLine();
                        String inputB = in.readLine();
                        String inputC = in.readLine();
                        if (inputA == null || inputA.trim().isEmpty() ||
                            inputB == null || inputB.trim().isEmpty() ||
                            inputC == null || inputC.trim().isEmpty()) {
                            System.out.println("No input provided. Please enter valid numbers.");
                            continue;
                        }
                        double a = Double.parseDouble(inputA);
                        double b = Double.parseDouble(inputB);
                        double c = Double.parseDouble(inputC);
                        commandManager.executeCommand(new SolveEquationCommand(calc, a, b, c));
                    } catch (IOException e) {
                        System.out.println("Error reading input: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                    }
                    break;
            }
        } while (s.charAt(0) != 'q');
    }

    /**
     * Точка входу в програму.
     * <p>
     * Створює об'єкт {@link Main} та викликає метод {@link #menu()} для запуску програми.
     * </p>
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        // Демонстрація паралельної обробки елементів колекції
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);

        if (numbers == null || numbers.isEmpty()) {
            System.out.println("The collection is empty. No tasks will be executed.");
            return;
        }

        ViewResult viewResult = new ViewResult();
        CommandQueue queue = new CommandQueue();

        // Додавання задач у чергу
        queue.addTask(new MinMaxCommand(numbers, viewResult));

        // Очікування завершення всіх задач
        while (!queue.isEmpty()) {
            try {
                Thread.sleep(100); // Перевіряємо чергу кожні 100 мс
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Завершення роботи
        queue.shutdown();

        // Відображення результатів
        viewResult.displayResults();

        // Запуск текстового меню
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}

