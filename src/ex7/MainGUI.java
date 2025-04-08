package ex7;

import ex3.ViewResult;
import ex5.*;
import ex6.ExecuteConsoleCommand;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ex2.EquationSolver;

import java.awt.*;

/**
 * Клас MainGUI реалізує графічний інтерфейс для роботи з командами.
 * <p>
 * Цей клас забезпечує відображення результатів у таблиці, текстовій області
 * та дозволяє виконувати різні операції через кнопки, такі як генерація даних,
 * збереження, відновлення, розв'язання рівнянь тощо.
 * </p>
 * 
 * @author Ваше ім'я
 * @version 1.0
 */
public class MainGUI {

    private ViewResult viewResult = new ViewResult(); // Об'єкт для зберігання результатів
    private CommandManager commandManager = CommandManager.getInstance(); // Менеджер команд
    private JTable resultTable; // Таблиця для відображення результатів
    private DefaultTableModel tableModel; // Модель таблиці
    private JTextArea messageArea; // Текстова область для повідомлень

    /**
     * Конструктор MainGUI.
     * <p>
     * Ініціалізує графічний інтерфейс, включаючи таблицю, текстову область
     * для повідомлень і кнопки для виконання різних операцій.
     * </p>
     */
    public MainGUI() {
        // Створення основного вікна
        JFrame frame = new JFrame("Graphical Interface for Commands");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Ініціалізація таблиці
        String[] columnNames = {"X", "Y"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        resultTable.setEnabled(false); // Забороняємо редагування
        frame.add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Текстова область для повідомлень
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setRows(5); // Встановлюємо кількість рядків для збільшення висоти
        messageArea.setLineWrap(true); // Додаємо перенесення рядків
        messageArea.setWrapStyleWord(true); // Перенесення по словах
        frame.add(new JScrollPane(messageArea), BorderLayout.NORTH);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        // Кнопка "View"
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> {
            updateTable();
            messageArea.setText("Results displayed.");
        });
        buttonPanel.add(viewButton);

        // Кнопка "Generate"
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> {
            String stepXStr = JOptionPane.showInputDialog(frame, "Enter stepX:");
            try {
                double stepX = Double.parseDouble(stepXStr);
                commandManager.executeCommand(new GenerateCommand(viewResult, stepX));
                updateTable();
                messageArea.setText("Generated results with stepX = " + stepX);
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input. Please enter a valid number.");
            }
        });
        buttonPanel.add(generateButton);

        // Кнопка "Save"
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                commandManager.executeCommand(new SaveCommand(viewResult));
                messageArea.setText("Results saved successfully.");
            } catch (Exception ex) {
                messageArea.setText("Error saving results: " + ex.getMessage());
            }
        });
        buttonPanel.add(saveButton);

        // Кнопка "Restore"
        JButton restoreButton = new JButton("Restore");
        restoreButton.addActionListener(e -> {
            try {
                commandManager.executeCommand(new RestoreCommand(viewResult));
                updateTable(); // Оновлюємо таблицю після відновлення
                messageArea.setText("Results restored.");
            } catch (Exception ex) {
                messageArea.setText("Error restoring results: " + ex.getMessage());
            }
        });
        buttonPanel.add(restoreButton);

        // Кнопка "Undo"
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            commandManager.undo();
            updateTable();
            messageArea.setText("Last operation undone.");
        });
        buttonPanel.add(undoButton);

        // Кнопка "Equation"
        JButton equationButton = new JButton("Equation");
        equationButton.addActionListener(e -> {
            try {
                String stepXStr = JOptionPane.showInputDialog(frame, "Enter stepX:");
                double stepX = Double.parseDouble(stepXStr);

                if (viewResult == null) {
                    System.out.println("Error: viewResult is not initialized.");
                    return;
                }

                viewResult.init(stepX);

                // Вивід результатів — у messageArea
                messageArea.setText(""); // Очистити перед виводом
                ExecuteConsoleCommand cmd = new ExecuteConsoleCommand(viewResult, msg -> {
                    messageArea.append(msg + "\n");
                });
                cmd.setMessageArea(messageArea); // Передаємо messageArea для відображення результатів
                cmd.execute();

                updateTable();

            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input. Please enter a valid number.");
            } catch (Exception ex) {
                messageArea.setText("Error executing equation: " + ex.getMessage());
            }
        });
        buttonPanel.add(equationButton);

        // Кнопка "Input Numbers"
        JButton inputNumbersButton = new JButton("Input Numbers");
        inputNumbersButton.addActionListener(e -> {
            String inputA = JOptionPane.showInputDialog(frame, "Enter coefficient a:");
            String inputB = JOptionPane.showInputDialog(frame, "Enter coefficient b:");
            String inputC = JOptionPane.showInputDialog(frame, "Enter coefficient c:");
            try {
                double a = Double.parseDouble(inputA);
                double b = Double.parseDouble(inputB);
                double c = Double.parseDouble(inputC);
                commandManager.executeCommand(new SolveEquationCommand(new EquationSolver(), viewResult, a, b, c)); // Виконання команди для розв'язання рівняння
                solveEquation(a, b, c); // Викликаємо метод для розрахунків
            } catch (NumberFormatException ex) {
                messageArea.setText("Invalid input. Please enter valid numbers.");
            }
        });
        buttonPanel.add(inputNumbersButton);

        // Кнопка "Exit"
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Відображення вікна
        frame.setVisible(true);
    }

    /**
     * Оновлює таблицю результатів.
     * <p>
     * Очищує таблицю та додає нові результати з об'єкта {@link ViewResult}.
     * </p>
     */
    private void updateTable() {
        // Очищуємо таблицю
        tableModel.setRowCount(0);

        // Отримуємо результати
        var results = viewResult.getResults();

        // Додаємо результати до таблиці
        for (var result : results) {
            tableModel.addRow(new Object[]{
                String.format("%.2f", result.getX()),
                String.format("%.3f", result.getY())
            });
        }
    }

    /**
     * Розв'язує квадратне рівняння та відображає результати.
     * 
     * @param a коефіцієнт при x^2
     * @param b коефіцієнт при x
     * @param c вільний член
     */
    private void solveEquation(double a, double b, double c) {
        try {
            // Виконання команди для розв'язання рівняння
            commandManager.executeCommand(new SolveEquationCommand(new EquationSolver(), viewResult, a, b, c));

            // Оновлення таблиці
            updateTable();

            // Формування повідомлення про результати
            StringBuilder resultMessage = new StringBuilder();
            var results = viewResult.getResultsAsArray();
            if (results.length == 0) {
                resultMessage.append("Немає дійсних коренів.");
            } else if (results.length == 1) {
                resultMessage.append("Єдиний корінь: x = ").append(results[0]);
            } else {
                resultMessage.append("Корені рівняння: x1 = ").append(results[0]).append(", x2 = ").append(results[1]);
            }

            // Виведення повідомлення
            messageArea.setText(resultMessage.toString());
        } catch (Exception ex) {
            // Обробка помилок
            messageArea.setText("Error solving equation: " + ex.getMessage());
        }
    }

    /**
     * Точка входу в програму.
     * <p>
     * Запускає графічний інтерфейс у потоці AWT.
     * </p>
     * 
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            System.out.println("viewResult: " + mainGUI.viewResult);
        });
    }
}