import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import ex2.EquationSolver;
import ex3.View;
import ex4.ViewableTable;

/**
 * Клас Main для запуску програми та управління користувацьким введенням.
 * <p>
 * Цей клас забезпечує текстове меню для взаємодії з користувачем,
 * дозволяючи виконувати різні операції, такі як перегляд результату,
 * генерація випадкових значень, збереження та відновлення даних.
 * </p>
 * 
 * @author xone
 * @version 1.1
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
     *     <li>'v' - Перегляд результату</li>
     *     <li>'g' - Генерація випадкових значень</li>
     *     <li>'s' - Збереження результату</li>
     *     <li>'r' - Відновлення результату</li>
     * </ul>
     * </p>
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
                    view.viewShow(); // Викликаємо метод для відображення результатів
                    break;
                case 'g':
                    double stepX = 1.0; // Встановіть крок для генерації
                    view.init(stepX); // Заповнення списку results
                    view.viewShow(); // Відображення результатів через view
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

