package ex5;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас MacroCommand реалізує макрокоманду, яка дозволяє виконувати кілька команд як одну.
 * <p>
 * Цей клас забезпечує виконання та скасування групи команд у визначеному порядку:
 * <ul>
 *     <li>Виконання команд відбувається у порядку їх додавання.</li>
 *     <li>Скасування команд відбувається у зворотному порядку.</li>
 * </ul>
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class MacroCommand implements Command {
    private List<Command> commands = new ArrayList<>(); // Список команд для виконання

    /**
     * Додає команду до макрокоманди.
     *
     * @param command Команда, яку потрібно додати.
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * Виконує всі команди, додані до макрокоманди.
     * <p>
     * Команди виконуються у порядку їх додавання.
     * </p>
     */
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    /**
     * Скасовує виконання всіх команд, доданих до макрокоманди.
     * <p>
     * Команди скасовуються у зворотному порядку їх виконання.
     * </p>
     */
    @Override
    public void undo() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}
