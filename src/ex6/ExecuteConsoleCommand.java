package ex6;

import ex3.ViewResult;
import ex5.Command;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.swing.JTextArea;

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
    private ViewResult viewResult; // Використовуємо ViewResult для обчислень
    private Consumer<String> output; // Для виводу повідомлень
    private JTextArea messageArea; // Для відображення результатів у графічному інтерфейсі

    public ExecuteConsoleCommand(ViewResult viewResult) {
        this(viewResult, System.out::println); // За замовчуванням — вивід у консоль
    }

    public ExecuteConsoleCommand(ViewResult viewResult, Consumer<String> output) {
        if (viewResult == null) {
            throw new IllegalArgumentException("viewResult cannot be null");
        }
        this.viewResult = viewResult;
        this.output = output;
    }

    public void setMessageArea(JTextArea messageArea) {
        this.messageArea = messageArea;
    }

    @Override
    public void execute() {
        if (viewResult.getIntegerResults().isEmpty()) {
            output.accept("No data available for processing.");
            return;
        }

        // Ініціалізація черг
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();

        // Ініціалізація команд
        MaxCommand maxCommand = new MaxCommand(viewResult.getIntegerResults(), messageArea);
        AvgCommand avgCommand = new AvgCommand(viewResult.getIntegerResults(), messageArea);
        MinMaxCommand minMaxCommand = new MinMaxCommand(viewResult.getIntegerResults(), messageArea, viewResult);

        output.accept("Execute all threads...");

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

            // Виведення повідомлення про завершення
            if (messageArea != null) {
                messageArea.append("All tasks completed.\n");
            } else {
                output.accept("All tasks completed.");
            }
        } catch (InterruptedException e) {
            output.accept("Interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        output.accept("All done.");
    }

    @Override
    public void undo() {
        output.accept("Undo operation is not supported for ExecuteConsoleCommand.");
    }
}
