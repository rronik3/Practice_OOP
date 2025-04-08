package ex5;

import ex2.EquationSolver;
import ex3.ViewResult;

/**
 * Клас SolveEquationCommand реалізує команду для розв'язання квадратного рівняння.
* <p>
 * Цей клас використовує об'єкт {@link EquationSolver} для обчислення коренів
 * квадратного рівняння з коефіцієнтами a, b, c. Результати обчислення виводяться
 * у консоль. Також підтримується скасування операції.
 * </p>
 * 
 * @author xone
 * @version 1.0
 */
public class SolveEquationCommand implements Command {
    private double a, b, c; // Коефіцієнти квадратного рівняння
    private double[] result; // Масив для збереження коренів рівняння
    private EquationSolver solver; // Об'єкт для обчислення рівняння
    private ViewResult viewResult; // Об'єкт для збереження результатів

    /**
     * Конструктор класу SolveEquationCommand.
     *
     * @param solver Об'єкт {@link EquationSolver} для обчислення коренів рівняння.
     * @param viewResult Об'єкт {@link ViewResult} для збереження результатів.
     * @param a Коефіцієнт при x^2.
     * @param b Коефіцієнт при x.
     * @param c Вільний член.
     */
    public SolveEquationCommand(EquationSolver solver, ViewResult viewResult, double a, double b, double c) {
        if (viewResult == null) {
            throw new IllegalArgumentException("viewResult cannot be null");
        }
        this.solver = solver;
        this.viewResult = viewResult;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Виконує команду для розв'язання квадратного рівняння.
* <p>
     * Обчислює корені рівняння за допомогою {@link EquationSolver#solveQuadraticEquation(double, double, double)}
     * і виводить їх у консоль. Якщо коренів немає, виводиться відповідне повідомлення.
     * </p>
     */
    @Override
    public void execute() {
    
        result = solver.solveQuadraticEquation(a, b, c);

        // Очищуємо попередні результати
        viewResult.clearResults();

        // Додаємо нові результати
        if (result.length == 2) {
            viewResult.addResult(a, b, c, result[0], result[1]); // Два корені
        } else if (result.length == 1) {
            viewResult.addResult(a, b, c, result[0]); // Один корінь
        }
    }

    /**
     * Скасовує виконання команди.
* <p>
     * Скидає результати обчислення і виводить повідомлення про скасування операції.
     * </p>
     */
    @Override
    public void undo() {
        if (viewResult == null) {
            throw new IllegalStateException("viewResult is not initialized");
        }

        System.out.println("Undoing SolveEquationCommand with viewResult: " + viewResult);

        result = null;
        viewResult.clearResults(); // Очищуємо результати у ViewResult
    }
}
