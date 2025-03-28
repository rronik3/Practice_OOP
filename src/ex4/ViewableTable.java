package ex4;

import ex3.ViewableResult;
import ex3.View;

/**
 * Клас ViewableTable розширює {@link ViewableResult}.
 * <p>
 * Забезпечує створення об'єкта {@link ViewTable}, який реалізує інтерфейс {@link View}.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class ViewableTable extends ViewableResult {
    /**
     * Створює об'єкт {@link ViewTable}.
     * <p>
     * Використовується для отримання конкретної реалізації інтерфейсу {@link View}.
     * </p>
     *
     * @return Об'єкт типу {@link ViewTable}, що реалізує інтерфейс {@link View}.
     */
    @Override
    public View getView() {
        return new ViewTable();
    }
}