package ex2;

/**
 * Клас MainTest для тестування функціональності класу {@link EquationSolver}.
 * <p>
 * Містить методи для перевірки обчислень та збереження/відновлення результатів.
 */
public class MainTest {

    /**
     * Точка входу для запуску тестів.
     * <p>
     * Викликає методи {@link #testCalc()} та {@link #testRestore()} для перевірки функціональності.
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        testCalc();
        testRestore();
    }

    /**
     * Тестує методи обчислення в класі {@link EquationSolver}.
     * <p>
     * Перевіряє, чи правильно обчислюється значення Y для заданих значень X.
     * <ul>
     *     <li>Якщо X = 0, очікується Y = 3.0</li>
     *     <li>Якщо X = 1, очікується Y = 9.0</li>
     * </ul>
     * Виводить результат тесту в консоль.
     */
    public static void testCalc() {
        EquationSolver calc = new EquationSolver();
        
        calc.initialize(0.0);
        if (Math.abs(calc.getResult().getY() - 3.0) < 0.0001) {
            System.out.println("testCalc (x = 0) Passed");
        } else {
            System.out.println("testCalc (x = 0) Failed");
        }

        calc.initialize(1.0);
        if (Math.abs(calc.getResult().getY() - 9.0) < 0.0001) {
            System.out.println("testCalc (x = 1) Passed");
        } else {
            System.out.println("testCalc (x = 1) Failed");
        }
    }

    /**
     * Тестує методи збереження та відновлення результатів у класі {@link EquationSolver}.
     * <p>
     * Ініціалізує об'єкт значенням X, зберігає результат, відновлює його
     * та перевіряє, чи значення X залишилося незмінним.
     * Виводить результат тесту в консоль.
     */
    public static void testRestore() {
        EquationSolver calc = new EquationSolver();
        double x = 2.0;
        calc.initialize(x);

        try {
            calc.saveResult();
            calc.restoreResult();

            if (Math.abs(calc.getResult().getX() - x) < 0.0001) {
                System.out.println("testRestore Passed");
            } else {
                System.out.println("testRestore Failed");
            }
        } catch (Exception e) {
            System.out.println("testRestore Exception occurred: " + e.getMessage());
        }
    }
}