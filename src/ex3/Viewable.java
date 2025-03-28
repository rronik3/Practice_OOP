package ex3;

/**
 * Інтерфейс для створення об'єктів, що реалізують інтерфейс {@link View}.
 * <p>
 * Забезпечує метод для отримання об'єкта, який відповідає за відображення результатів.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public interface Viewable {
    /**
     * Створює об'єкт, що реалізує інтерфейс {@link View}.
     * <p>
     * Використовується для отримання конкретної реалізації інтерфейсу {@link View}.
     * </p>
     *
     * @return Об'єкт, що реалізує інтерфейс {@link View}.
     */
    public View getView();
}

