import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import ex2.EquationSolver;
import ex3.View;
import ex4.ViewableTable;
import ex5.CommandManager;
import ex5.GenerateCommand;
import ex5.SaveCommand;
import ex5.RestoreCommand;
import ex5.SolveEquationCommand;

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
            System.out.println("Enter command: 'q'uit, 'v'iew, 'g'enerate, 's'ave, 'r'estore, 'u'ndo, 'e'quation");
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
                default:
                    // Невідома команда
                    System.out.println("Invalid command.");
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
        Main main = new Main(new ViewableTable().getView()); // Використовуємо ViewableTable для створення View
        main.menu();
    }
}

