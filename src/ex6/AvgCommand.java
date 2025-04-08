package ex6;

import java.util.List;

import javax.swing.JTextArea;

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

    private JTextArea messageArea;

    public AvgCommand(List<Integer> list, JTextArea messageArea) {
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
            String msg = "The collection is empty. Cannot calculate the average.\n";
            if (messageArea != null) {
                messageArea.append(msg);
            } else {
                System.out.println(msg);
            }
            return;
        }
    
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElseThrow();
        String msg = "Average value: " + avg + "\n";
        if (messageArea != null) {
            messageArea.append(msg);
        } else {
            System.out.println(msg);
        }
    }
}