package ex5;

import ex2.EquationSolver;

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

    /**
     * Конструктор класу SolveEquationCommand.
     *
     * @param solver Об'єкт {@link EquationSolver} для обчислення коренів рівняння.
     * @param a Коефіцієнт при x^2.
     * @param b Коефіцієнт при x.
     * @param c Вільний член.
     */
    public SolveEquationCommand(EquationSolver solver, double a, double b, double c) {
        this.solver = solver;
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
        if (result.length == 2) {
            System.out.printf("Roots of an equation: x1 = %.2f, x2 = %.2f%n", result[0], result[1]);
        } else if (result.length == 1) {
            System.out.printf("Root of an equation: x = %.2f%n", result[0]);
        } else {
            System.out.println("There are no real roots.");
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
        result = null;
        System.out.println("The operation has been canceled.");
    }
}
