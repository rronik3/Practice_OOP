package ex6;

import java.util.List;

import javax.swing.JTextArea;

import ex3.ViewResult;
import ex5.Command;

/**
 * Клас MinMaxCommand реалізує команду для обчислення мінімального та максимального значень.
 * <p>
 * Цей клас використовує шаблон Command і може бути доданий до черги завдань
 * для паралельного виконання. Команда обчислює мінімальне та максимальне значення
 * в списку чисел і виводить результати у текстову область.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class MinMaxCommand implements Command {
    /** Список чисел для обчислення мінімального та максимального значень. */
    private List<Integer> data;

    private JTextArea messageArea;

    public MinMaxCommand(List<Integer> data, JTextArea messageArea, ViewResult viewResult) {
        this.data = data;
        this.messageArea = messageArea;
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Виводить повідомлення про те, що скасування не підтримується для цієї команди.
     * </p>
     */
    @Override
    public void undo() {
        messageArea.append("Undo operation is not supported for MinMaxCommand.\n");
    }

    /**
     * Виконує команду для обчислення мінімального та максимального значень.
     * <p>
     * Якщо список чисел порожній або дорівнює {@code null}, виводиться відповідне повідомлення.
     * В іншому випадку обчислюються мінімальне та максимальне значення в списку,
     * і результати виводяться у текстову область.
     * </p>
     */
    @Override
    public void execute() {
        if (data == null || data.isEmpty()) {
            messageArea.append("The collection is empty. Cannot find min and max values.\n");
            return;
        }

        int min = data.stream().min(Integer::compare).orElse(Integer.MAX_VALUE);
        int max = data.stream().max(Integer::compare).orElse(Integer.MIN_VALUE);

        messageArea.append("Min value: " + min + ", Max value: " + max + "\n");
    }
}
