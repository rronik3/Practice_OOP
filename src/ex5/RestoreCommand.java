package ex5;

import ex3.View;

/**
 * Клас RestoreCommand реалізує команду для відновлення результатів.
 * <p>
 * Цей клас використовує об'єкт {@link View} для відновлення збережених результатів.
 * Команда підтримує виконання операції, але не підтримує скасування.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class RestoreCommand implements Command {
    private View view;

    /**
     * Конструктор класу RestoreCommand.
     *
     * @param view Об'єкт {@link View}, який використовується для відновлення результатів.
     */
    public RestoreCommand(View view) {
        this.view = view;
    }

    /**
     * Виконує команду для відновлення результатів.
     * <p>
     * Викликає метод {@link View#viewRestore()} для відновлення даних
     * та виводить повідомлення про успішне виконання або помилку.
     * </p>
     */
    @Override
    public void execute() {
        try {
            view.viewRestore();
            System.out.println("Results restored successfully.");
        } catch (Exception e) {
            System.out.println("Error restoring results: " + e.getMessage());
        }
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Ця команда не підтримує скасування, тому виводиться відповідне повідомлення.
     * </p>
     */
    @Override
    public void undo() {
        System.out.println("Undo not supported for RestoreCommand.");
    }
}
