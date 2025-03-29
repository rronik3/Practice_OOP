package ex2;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Клас для вирішення рівняння і збереження результату.
 * <p>
 * Забезпечує обчислення значення рівняння, збереження результату у файл
 * та відновлення результату з файлу.
 */
public class EquationSolver {
    private static final String FNAME = "Result.bin"; // Файл для серіалізації результату
    private EquationData result; // Об'єкт для зберігання результату

    /**
     * Конструктор класу {@code EquationSolver}.
     * <p>
     * Ініціалізує об'єкт {@link EquationData} для зберігання результатів.
     */
    public EquationSolver() {
        result = new EquationData();
    }

    /**
     * Встановлює об'єкт результату.
     *
     * @param result Об'єкт {@link EquationData}, який потрібно встановити.
     */
    public void setResult(EquationData result) {
        this.result = result;
    }

    /**
     * Повертає об'єкт результату.
     *
     * @return Об'єкт {@link EquationData}, що містить результат.
     */
    public EquationData getResult() {
        return result;
    }

    /**
     * Обчислює значення рівняння для заданого X.
     * <p>
     * Формула рівняння: {@code y = x^2 + 5x + 3}.
     *
     * @param x Значення X.
     * @return Обчислене значення Y.
     */
    private double solveEquation(double x) {
        return Math.pow(x, 2) + 5 * x + 3; // Приклад рівняння
    }

    /**
     * Ініціалізує об'єкт результату заданим значенням X.
     * <p>
     * Обчислює значення Y за допомогою рівняння та зберігає його.
     *
     * @param x Значення X.
     * @return Обчислене значення Y.
     */
    public double initialize(double x) {
        result.setX(x);
        return result.setY(solveEquation(x));
    }

    /**
     * Виводить результат у консоль.
     */
    public void displayResult() {
        System.out.println(result);
    }

    /**
     * Зберігає результат у файл.
     *
     * @throws IOException Якщо виникає помилка під час запису у файл.
     */
    public void saveResult() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME))) {
            os.writeObject(result);
            os.flush();
        }
    }

    /**
     * Відновлює результат із файлу.
     *
     * @throws Exception Якщо виникає помилка під час читання з файлу.
     */
    public void restoreResult() throws Exception {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME))) {
            result = (EquationData) is.readObject();
        }
    }
     /**
     * Розв'язує квадратне рівняння ax^2 + bx + c = 0.
     *
     * @param a Коефіцієнт при x^2.
     * @param b Коефіцієнт при x.
     * @param c Вільний член.
     * @return Масив із коренями рівняння. Якщо коренів немає, повертається порожній масив.
     */
    public double[] solveQuadraticEquation(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Коефіцієнт 'a' не може бути нулем.");
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{x1, x2};
        } else if (discriminant == 0) {
            double x = -b / (2 * a);
            return new double[]{x};
        } else {
            return new double[]{}; // Немає дійсних коренів
        }
    }
}
