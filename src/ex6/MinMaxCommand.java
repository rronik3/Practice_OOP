package ex6;

import java.util.List;
import ex5.Command;
import ex3.ViewResult;

/**
 * Клас MinMaxCommand реалізує команду для обчислення мінімального та максимального значень.
 * <p>
 * Цей клас використовує шаблон Command і може бути доданий до черги завдань
 * для паралельного виконання. Команда обчислює мінімальне та максимальне значення
 * в списку чисел і зберігає результати у {@link ViewResult}.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class MinMaxCommand implements Command {
    /** Список чисел для обчислення мінімального та максимального значень. */
    private final List<Integer> numbers;

    /** Об'єкт для збереження результатів обчислень. */
    private final ViewResult viewResult;

    /**
     * Конструктор класу MinMaxCommand.
     *
     * @param numbers Список чисел для обчислення.
     * @param viewResult Об'єкт {@linkplain ViewResult} для збереження результатів.
     */
    public MinMaxCommand(List<Integer> numbers, ViewResult viewResult) {
        this.numbers = numbers;
        this.viewResult = viewResult;
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Виводить повідомлення про те, що скасування не підтримується для цієї команди.
     * </p>
     */
    @Override
    public void undo() {
        System.out.println("Undo operation is not supported.");
    }

    /**
     * Виконує команду для обчислення мінімального та максимального значень.
     * <p>
     * Якщо список чисел порожній або дорівнює {@code null}, виводиться відповідне повідомлення.
     * В іншому випадку обчислюються мінімальне та максимальне значення в списку,
     * і результати зберігаються у {@link ViewResult}.
     * </p>
     */
    @Override
    public void execute() {
        if (numbers == null || numbers.isEmpty()) {
            System.out.println("The collection is empty. Cannot find min and max values.");
            return;
        }
        if (viewResult == null) {
            System.out.println("ViewResult is not initialized. Cannot store results.");
            return;
        }

        // Обчислення мінімального та максимального значень
        int min = numbers.stream().min(Integer::compareTo).orElseThrow();
        int max = numbers.stream().max(Integer::compareTo).orElseThrow();

        // Збереження результатів у ViewResult
        viewResult.addAdditionalResult("Min value", min);
        viewResult.addAdditionalResult("Max value", max);

        System.out.println("Min and Max values have been calculated and stored.");
    }
}
