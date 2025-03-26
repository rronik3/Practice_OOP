package ex3;

/**
 * Клас ViewableResult реалізує інтерфейс {@link Viewable}.
 * <p>
 * Забезпечує створення об'єкта типу {@link ViewResult}, який реалізує інтерфейс {@link View}.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class ViewableResult implements Viewable {
    /**
     * Створює об'єкт типу {@link ViewResult}.
     * <p>
     * Використовується для отримання конкретної реалізації інтерфейсу {@link View}.
     * </p>
     *
     * @return Об'єкт типу {@link ViewResult}, що реалізує інтерфейс {@link View}.
     */
    @Override
    public View getView() {
        return new ViewResult();
    }
}
