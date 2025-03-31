package ex6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import ex5.Command;

/**
 * Клас CommandQueue реалізує чергу завдань для Worker Thread.
 * <p>
 * Завдання додаються в чергу та виконуються окремим потоком. 
 * Цей клас забезпечує асинхронне виконання команд, використовуючи шаблон Worker Thread.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class CommandQueue {
    /** Черга завдань для виконання. */
    private final BlockingQueue<Command> taskQueue = new LinkedBlockingQueue<>();

    /** Потік, який обробляє завдання з черги. */
    private final Thread workerThread;

    /** Прапорець для контролю роботи потоку. */
    private volatile boolean isRunning = true;

    /**
     * Конструктор класу CommandQueue.
     * <p>
     * Ініціалізує потік Worker Thread і запускає його для обробки завдань.
     * </p>
     */
    public CommandQueue() {
        workerThread = new Thread(this::processTasks, "Worker-Thread");
        workerThread.start();
    }

    /**
     * Додає завдання до черги.
     *
     * @param task Завдання, яке потрібно виконати.
     */
    public void addTask(Command task) {
        taskQueue.offer(task);
    }

    /**
     * Перевіряє, чи черга завдань порожня.
     *
     * @return {@code true}, якщо черга порожня, інакше {@code false}.
     */
    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    /**
     * Обробляє завдання з черги.
     * <p>
     * Потік виконує завдання, поки прапорець {@code isRunning} встановлений у {@code true}
     * або поки черга не стане порожньою.
     * </p>
     */
    private void processTasks() {
        while (isRunning || !taskQueue.isEmpty()) {
            try {
                Command task = taskQueue.poll();
                if (task != null) {
                    task.execute();
                }
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Завершує роботу потоку Worker Thread.
     * <p>
     * Встановлює прапорець {@code isRunning} у {@code false} і чекає завершення потоку.
     * </p>
     */
    public void shutdown() {
        isRunning = false;
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
