package ex6;

import ex3.ViewResult;
import ex5.Command;
import java.util.concurrent.TimeUnit;

/**
 * Клас ExecuteConsoleCommand реалізує команду для виконання всіх потоків.
 * <p>
 * Використовує шаблон Worker Thread для виконання завдань, таких як обчислення
 * мінімального, максимального значень і середнього значення. Завдання виконуються
 * паралельно через черги {@link CommandQueue}.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class ExecuteConsoleCommand implements Command {
    /** Об'єкт, який реалізує інтерфейс {@linkplain ViewResult}. */
    private ViewResult view;

    /**
     * Конструктор, який ініціалізує поле {@linkplain ExecuteConsoleCommand#view}.
     *
     * @param view Об'єкт {@linkplain ViewResult}, який містить дані для обробки.
     */
    public ExecuteConsoleCommand(ViewResult view) {
        this.view = view;
    }

    /**
     * Виконує команду для запуску всіх потоків.
     * <p>
     * Створює дві черги завдань {@link CommandQueue} і додає до них завдання:
     * <ul>
     *     <li>{@link MinMaxCommand} для обчислення мінімального та максимального значень.</li>
     *     <li>{@link MaxCommand} для обчислення максимального значення.</li>
     *     <li>{@link AvgCommand} для обчислення середнього значення.</li>
     * </ul>
     * Потоки виконуються паралельно, і метод чекає завершення всіх завдань.
     * </p>
     * 
     * @throws InterruptedException Якщо потік було перервано під час очікування завершення завдань.
     */
    @Override
    public void execute() {
        if (view.getIntegerResults().isEmpty()) {
            System.out.println("No data available for processing.");
            return;
        }

        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();

        MaxCommand maxCommand = new MaxCommand(view.getIntegerResults());
        AvgCommand avgCommand = new AvgCommand(view.getIntegerResults());
        MinMaxCommand minMaxCommand = new MinMaxCommand(view.getIntegerResults(), view);

        System.out.println("Execute all threads...");

        // Додавання завдань у черги
        queue1.addTask(minMaxCommand);
        queue2.addTask(maxCommand);
        queue2.addTask(avgCommand);

        // Очікування завершення всіх завдань
        try {
            while (!queue1.isEmpty() || !queue2.isEmpty()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue1.shutdown();
            queue2.shutdown();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e);
            Thread.currentThread().interrupt();
        }

        System.out.println("All done.");
    }

    /**
     * Скасовує виконання команди.
     * <p>
     * Виводить повідомлення про те, що скасування не підтримується для цієї команди.
     * </p>
     */
    @Override
    public void undo() {
        System.out.println("Undo operation is not supported for ExecuteConsoleCommand.");
    }
}
