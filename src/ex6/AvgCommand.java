package ex6;

import java.util.List;
import ex5.Command;

/**
 * Клас AvgCommand реалізує завдання для обчислення середнього значення.
 * <p>
 * Цей клас використовує шаблон Command і може бути доданий до черги завдань
 * для паралельного виконання. Команда обчислює середнє значення чисел у списку
 * і виводить результат у консоль.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class AvgCommand implements Command {
    /** Список чисел для обчислення середнього значення. */
    private final List<Integer> numbers;

    /**
     * Конструктор класу AvgCommand.
     *
     * @param numbers Список чисел для обчислення середнього значення.
     */
    public AvgCommand(List<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Виводить повідомлення про те, що скасування не підтримується для цієї команди.
     * </p>
     */
    @Override
    public void undo() {
        System.out.println("Undo operation is not supported for AvgCommand.");
    }

    /**
     * Виконує команду для обчислення середнього значення.
     * <p>
     * Якщо список чисел порожній або дорівнює {@code null}, виводиться відповідне повідомлення.
     * В іншому випадку обчислюється середнє значення чисел у списку і виводиться в консоль.
     * </p>
     */
    @Override
    public void execute() {
        if (numbers == null || numbers.isEmpty()) {
            System.out.println("The collection is empty. Cannot calculate the average.");
            return;
        }
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElseThrow();
        System.out.println("Average value: " + avg);
    }
}