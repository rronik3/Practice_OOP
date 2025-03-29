package ex5;

import java.util.Stack;

/**
 * Клас CommandManager реалізує менеджер для управління виконанням і скасуванням команд.
 * <p>
 * Цей клас використовує шаблон Singleton для забезпечення єдиного екземпляра менеджера.
 * Він зберігає виконані команди у стеку, що дозволяє скасовувати останню виконану команду.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class CommandManager {
    private static CommandManager instance; // Єдиний екземпляр класу
    private Stack<Command> commandStack = new Stack<>(); // Стек для зберігання виконаних команд

    /**
     * Приватний конструктор для реалізації шаблону Singleton.
     */
    private CommandManager() {}

    /**
     * Повертає єдиний екземпляр класу CommandManager.
     * <p>
     * Якщо екземпляр ще не створено, він створюється під час першого виклику.
     * </p>
     *
     * @return Єдиний екземпляр класу CommandManager.
     */
    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    /**
     * Виконує команду та додає її до стеку.
     *
     * @param command Команда, яку потрібно виконати.
     */
    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
    }

    /**
     * Скасовує останню виконану команду.
     * <p>
     * Якщо стек команд порожній, виводиться повідомлення про відсутність команд для скасування.
     * </p>
     */
    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            command.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}
