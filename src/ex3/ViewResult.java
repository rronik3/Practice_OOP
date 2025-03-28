package ex3;

import ex2.EquationData;
import java.io.*;
import java.util.ArrayList;

/**
 * Клас ViewResult реалізує інтерфейс {@link View}.
 * <p>
 * Забезпечує зберігання, ініціалізацію, відображення та серіалізацію результатів обчислень.
 * </p>
 * 
 * @author xone
 * @version 1.1
 */
public class ViewResult implements View {
    /** Ім'я файлу для серіалізації */
    private static final String FNAME = "equations.bin";
    /** Кількість початкових елементів */
    private static final int DEFAULT_NUM = 10;
    /** Колекція результатів рівнянь */
    protected ArrayList<EquationData> results = new ArrayList<>();

    /**
     * Конструктор за замовчуванням.
     * <p>
     * Викликає конструктор {@link ViewResult#ViewResult(int)} з параметром за замовчуванням.
     * </p>
     */
    public ViewResult() {
        this(DEFAULT_NUM);
    }

    /**
     * Конструктор із параметром.
     * <p>
     * Ініціалізує список результатів рівнянь заданою кількістю елементів.
     * </p>
     *
     * @param n початкова кількість елементів
     */
    public ViewResult(int n) {
        for (int i = 0; i < n; i++) {
            results.add(new EquationData());
        }
    }

    /**
     * Повертає список результатів рівнянь.
     *
     * @return Список результатів рівнянь.
     */
    public ArrayList<EquationData> getResults() {
        return results;
    }

    /**
     * Метод для обчислення значення рівняння.
     * <p>
     * Використовується для обчислення значення Y на основі X.
     * </p>
     *
     * @param x Значення X.
     * @return Обчислене значення Y.
     */
    public double calculate(double x) {
        return x * x + 5 * x + 3; // Формула для обчислення y
    }

    /**
     * Заповнює список результатів обчисленими значеннями.
     *
     * @param stepX Крок зміни значення X.
     */
    public void init(double stepX) {
        double x = 0.0;
        results.clear(); // Очищення списку перед заповненням
        for (int i = 0; i < 10; i++) { // Наприклад, 10 значень
            EquationData data = new EquationData();
            data.setX(x);
            data.setY(calculate(x)); // Використання методу calculate для обчислення y
            results.add(data);
            x += stepX; // Збільшення x на крок
        }
    }

    /**
     * Ініціалізує список результатів випадковими значеннями.
     */
    @Override
    public void viewInit() {
        init(Math.random() * 360.0);
    }

    /**
     * Зберігає список результатів у файл.
     *
     * @throws IOException Якщо виникає помилка під час запису у файл.
     */
    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(results);
        os.flush();
        os.close();
    }

    /**
     * Відновлює список результатів із файлу.
     *
     * @throws Exception Якщо виникає помилка під час читання з файлу.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        results = (ArrayList<EquationData>) is.readObject();
        is.close();
    }

    /**
     * Виводить заголовок результатів.
     */
    @Override
    public void viewHeader() {
        System.out.println("Результати обчислень:");
    }

    /**
     * Виводить основні результати.
     */
    @Override
    public void viewBody() {
        for (EquationData data : results) {
            System.out.printf("(%.2f; %.3f) ", data.getX(), data.getY());
        }
        System.out.println();
    }

    /**
     * Виводить завершення результатів.
     */
    @Override
    public void viewFooter() {
        System.out.println("Кінець.");
    }

    /**
     * Виводить всі результати.
     * <p>
     * Викликає методи {@link #viewHeader()}, {@link #viewBody()} та {@link #viewFooter()}.
     * </p>
     */
    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }

    /**
     * Відображає таблицю результатів для рівняння.
     *
     * @param a Початкове значення X.
     * @param b Кінцеве значення X.
     * @param c Кількість кроків.
     */
    public void showTable(double a, double b, double c) {
        System.out.println("Результати для рівняння:");

        // Ініціалізація таблиці з кроком stepX
        double stepX = (b - a) / c; // Розрахунок кроку
        init(stepX); // Заповнення списку results

        // Відображення таблиці
        viewHeader();
        viewBody();
        viewFooter();
    }

    /**
     * Точка входу в програму.
     * <p>
     * Створює об'єкт {@link ViewResult} та викликає метод {@link #showTable(double, double, double)}
     * для демонстрації роботи класу.
     * </p>
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        ViewResult viewResult = new ViewResult();
        viewResult.showTable(0, 10, 10); // Від 0 до 10 з 10 кроками
    }
}
