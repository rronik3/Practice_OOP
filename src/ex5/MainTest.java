package ex5;

import ex3.ViewResult;
import ex4.ViewTable;

/**
 * Клас MainTest використовується для тестування функціональності програми.
 * <p>
 * Цей клас перевіряє:
 * <ul>
 *     <li>Виконання команд, таких як {@link GenerateCommand} і {@link SaveCommand}.</li>
 *     <li>Скасування команд за допомогою {@link CommandManager#undo()}.</li>
 *     <li>Роботу макрокоманд через {@link MacroCommand}.</li>
 * </ul>
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class MainTest {
    /**
     * Точка входу для тестування функціональності програми.
     * <p>
     * Виконує наступні тести:
     * <ul>
     *     <li>Тестування генерації даних.</li>
     *     <li>Тестування скасування операцій.</li>
     *     <li>Тестування виконання макрокоманд.</li>
     * </ul>
     * </p>
     *
     * @param args Аргументи командного рядка (не використовуються).
     */
    public static void main(String[] args) {
        ViewResult view = new ViewTable();
        CommandManager commandManager = CommandManager.getInstance();

        // Тестування генерації
        Command generateCommand = new GenerateCommand(view, 1.0);
        commandManager.executeCommand(generateCommand);

        // Тестування скасування
        commandManager.undo();

        // Тестування макрокоманди
        MacroCommand macroCommand = new MacroCommand();
        macroCommand.addCommand(new GenerateCommand(view, 1.0));
        macroCommand.addCommand(new SaveCommand(view));
        commandManager.executeCommand(macroCommand);

        // Скасування макрокоманди
        commandManager.undo();
    }
}
