package ex2;

import java.io.Serializable;

/**
 * Клас для зберігання результатів обчислень.
 * <p>
 * Реалізує інтерфейс {@link Serializable} для можливості серіалізації об'єктів.
 * Зберігає значення X та Y, які використовуються у рівняннях.
 */
public class EquationData implements Serializable {
    private double x; // Значення X
    private double y; // Значення Y

    private static final long serialVersionUID = 1L; // Версія серіалізації

    /**
     * Конструктор за замовчуванням.
     * <p>
     * Ініціалізує значення X та Y нулями.
     */
    public EquationData() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Конструктор із параметрами.
     * <p>
     * Ініціалізує значення X та Y заданими параметрами.
     *
     * @param x Значення X.
     * @param y Значення Y.
     */
    public EquationData(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Встановлює значення X.
     *
     * @param x Значення X.
     * @return Встановлене значення X.
     */
    public double setX(double x) {
        this.x = x;
        return this.x;
    }

    /**
     * Повертає значення X.
     *
     * @return Значення X.
     */
    public double getX() {
        return x;
    }

    /**
     * Встановлює значення Y.
     *
     * @param y Значення Y.
     * @return Встановлене значення Y.
     */
    public double setY(double y) {
        this.y = y;
        return this.y;
    }

    /**
     * Повертає значення Y.
     *
     * @return Значення Y.
     */
    public double getY() {
        return y;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     * <p>
     * Формат: {@code "x = [значення X], y = [значення Y]"}.
     *
     * @return Рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    /**
     * Перевіряє рівність двох об'єктів {@code EquationData}.
     * <p>
     * Об'єкти вважаються рівними, якщо їх значення X та Y однакові.
     *
     * @param obj Об'єкт для порівняння.
     * @return {@code true}, якщо об'єкти рівні; {@code false} інакше.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EquationData result = (EquationData) obj;
        return Double.compare(result.x, x) == 0 && Double.compare(result.y, y) == 0;
    }
}
