package ex4;

public class MainTest {
    
    public static void main(String[] args) {
        testDiscriminantPositive();
        testDiscriminantZero();
        testDiscriminantNegative();
    }

    static void testDiscriminantPositive() {
        ViewTable table = new ViewTable();
        System.out.println("Тест з позитивним дискримінантом:");
        table.showTable(1, -3, 2); // Очікувані корені: x1=2, x2=1
        System.out.println("Тест завершено.\n");
    }

    static void testDiscriminantZero() {
        ViewTable table = new ViewTable();
        System.out.println("Тест з дискримінантом рівним нулю:");
        table.showTable(1, -2, 1); // Очікуваний корінь: x=1
        System.out.println("Тест завершено.\n");
    }

    static void testDiscriminantNegative() {
        ViewTable table = new ViewTable();
        System.out.println("Тест з негативним дискримінантом:");
        table.showTable(1, 1, 1); // Очікувано: немає дійсних коренів
        System.out.println("Тест завершено.\n");
    }
}

