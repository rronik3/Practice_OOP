package ex4;

import java.util.Formatter;
import ex2.EquationData;
import ex3.ViewResult;

/**
 * Клас ViewTable розширює {@link ViewResult}.
 * <p>
 * Забезпечує відображення результатів у вигляді таблиці з можливістю налаштування ширини.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class ViewTable extends ViewResult {
    private static final int DEFAULT_WIDTH = 20; // Ширина таблиці за замовчуванням
    private int width; // Поточна ширина таблиці

    /**
     * Конструктор за замовчуванням.
     * <p>
     * Ініціалізує ширину таблиці значенням за замовчуванням.
     * </p>
     */
    public ViewTable() {
        width = DEFAULT_WIDTH;
    }

    /**
     * Конструктор із параметром ширини.
     *
     * @param width Ширина таблиці.
     */
    public ViewTable(int width) {
        this.width = width;
    }

    /**
     * Конструктор із параметрами ширини та кількості елементів.
     *
     * @param width Ширина таблиці.
     * @param n Кількість елементів у списку результатів.
     */
    public ViewTable(int width, int n) {
        super(n);
        this.width = width;
    }

    /**
     * Встановлює ширину таблиці.
     *
     * @param width Нова ширина таблиці.
     * @return Встановлена ширина таблиці.
     */
    public int setWidth(int width) {
        return this.width = width;
    }

    /**
     * Повертає поточну ширину таблиці.
     *
     * @return Поточна ширина таблиці.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Виводить горизонтальну лінію таблиці.
     */
    private void outLine() {
        for (int i = width; i > 0; i--) {
            System.out.print('-');
        }
    }

    /**
     * Виводить горизонтальну лінію таблиці з переходом на новий рядок.
     */
    private void outLineLn() {
        outLine();
        System.out.println();
    }

    /**
     * Виводить заголовок таблиці.
     */
    private void outHeader() {
        Formatter fmt = null;
        try {
            fmt = new Formatter();
            fmt.format("%s%d%s%2$d%s", "%", (width - 3) / 2, "s | %", "s\n");
            System.out.printf(fmt.toString(), "x ", "y ");
        } finally {
            if (fmt != null) {
                fmt.close();
            }
        }
    }

    /**
     * Виводить основну частину таблиці.
     */
    private void outBody() {
        Formatter fmt = null;
        try {
            fmt = new Formatter();
            fmt.format("%s%d%s%2$d%s", "%", (width - 3) / 2, ".0f | %", ".3f\n");
            for (EquationData equationData : getResults()) {
                System.out.printf(fmt.toString(), equationData.getX(), equationData.getY());
            }
        } finally {
            if (fmt != null) {
                fmt.close();
            }
        }
    }

    /**
     * Ініціалізує таблицю з вказаною шириною.
     *
     * @param width Ширина таблиці.
     */
    public final void init(int width) {
        this.width = width;
        viewInit();
    }

    /**
     * Ініціалізує таблицю з вказаною шириною та кроком.
     *
     * @param width Ширина таблиці.
     * @param stepX Крок зміни значення X.
     */
    public final void init(int width, double stepX) {
        this.width = width;
        init(stepX);
    }

    /**
     * Ініціалізує список результатів із випадковим початковим значенням X.
     *
     * @param stepX Крок зміни значення X.
     */
    @Override
    public void init(double stepX) {
        double x = Math.random() * 10; // Випадкове початкове значення від 0 до 10
        results.clear(); // Очищення списку перед заповненням
        for (int i = 0; i < 10; i++) { // Наприклад, 10 значень
            EquationData data = new EquationData();
            data.setX(x);
            data.setY(calculate(x)); // Використання методу calculate для обчислення y
            results.add(data); // Додавання об'єкта до списку
            x += stepX; // Збільшення x на крок
        }
    }

    /**
     * Виводить заголовок таблиці.
     */
    @Override
    public void viewHeader() {
        System.out.println("Data");
    }

    /**
     * Виводить основну частину таблиці.
     */
    @Override
    public void viewBody() {
        for (EquationData data : getResults()) {
            System.out.printf("x = %.2f, y = %.3f%n", data.getX(), data.getY());
        }
    }

    /**
     * Виводить завершення таблиці.
     */
    @Override
    public void viewFooter() {
        System.out.println("End of the table");
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
        double stepX = (b - a) / c; // Розрахунок кроку
        init(stepX); // Заповнення списку results
        viewHeader();
        viewBody();
        viewFooter();
    }
}