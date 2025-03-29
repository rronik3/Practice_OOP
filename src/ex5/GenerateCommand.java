package ex5;

import ex3.View;

/**
 * Клас GenerateCommand реалізує команду для генерації даних.
 * <p>
 * Цей клас використовує об'єкт {@link View} для ініціалізації даних із заданим кроком
 * {@code stepX} і відображення результатів. Також підтримується скасування операції.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class GenerateCommand implements Command {
    private View view; // Об'єкт для роботи з даними
    private double stepX; // Крок для генерації даних

    /**
     * Конструктор класу GenerateCommand.
     *
     * @param view Об'єкт {@link View} для роботи з даними.
     * @param stepX Крок для генерації даних.
     */
    public GenerateCommand(View view, double stepX) {
        this.view = view;
        this.stepX = stepX;
    }

    /**
     * Виконує команду для генерації даних.
     * <p>
     * Ініціалізує дані з використанням заданого кроку {@code stepX} і відображає результати.
     * </p>
     */
    @Override
    public void execute() {
        view.init(stepX);
        view.viewShow();
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Виводить повідомлення про скасування генерації. Реалізація логіки скасування
     * може бути додана за потреби.
     * </p>
     */
    @Override
    public void undo() {
        System.out.println("Undoing generation...");
        // Реалізуйте логіку скасування, якщо потрібно
    }
}
