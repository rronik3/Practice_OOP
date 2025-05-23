package ex6;

import java.util.List;

import javax.swing.JTextArea;

import ex5.Command;

/**
 * Клас MaxCommand реалізує команду для обчислення максимального значення в списку чисел.
 * <p>
 * Цей клас використовує шаблон Command і може бути доданий до черги завдань
 * для паралельного виконання. Команда обчислює максимальне значення в списку
 * і виводить його в консоль.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class MaxCommand implements Command {
    /** Список чисел для обчислення максимального значення. */
    private final List<Integer> numbers;

    private JTextArea messageArea;

    public MaxCommand(List<Integer> list, JTextArea messageArea) {
        this.numbers = list;
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
        System.out.println("Undo operation is not supported for MaxCommand.");
    }

    /**
     * Виконує команду для обчислення максимального значення.
     * <p>
     * Якщо список чисел порожній або дорівнює {@code null}, виводиться відповідне повідомлення.
     * В іншому випадку обчислюється максимальне значення в списку і виводиться в консоль.
     * </p>
     */
    @Override
    public void execute() {
        if (numbers == null || numbers.isEmpty()) {
            if (messageArea != null) {
                messageArea.append("The collection is empty. Cannot find the maximum value.\n");
            } else {
                System.out.println("The collection is empty. Cannot find the maximum value.");
            }
            return;
        }
        int max = numbers.stream().max(Integer::compareTo).orElseThrow();
        if (messageArea != null) {
            messageArea.append("Max value: " + max + "\n");
        } else {
            System.out.println("Max value: " + max);
        }
    }
}
