package ex5;

import ex3.View;
import java.io.IOException;

/**
 * Клас SaveCommand реалізує команду для збереження результатів.
 * <p>
 * Цей клас використовує об'єкт {@link View} для збереження результатів у файл.
 * Команда підтримує виконання операції, але не підтримує скасування.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class SaveCommand implements Command {
    private View view;

    /**
     * Конструктор класу SaveCommand.
     *
     * @param view Об'єкт {@link View}, який використовується для збереження результатів.
     */
    public SaveCommand(View view) {
        this.view = view;
    }

    /**
     * Виконує команду для збереження результатів.
     * <p>
     * Викликає метод {@link View#viewSave()} для збереження даних
     * та виводить повідомлення про успішне виконання або помилку.
     * </p>
     */
    @Override
    public void execute() {
        try {
            view.viewSave();
            System.out.println("Results saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving results: " + e.getMessage());
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
        System.out.println("Undo not supported for SaveCommand.");
    }
}
