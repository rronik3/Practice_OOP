package ex5;

import ex3.View;
import java.io.IOException;

public class SaveCommand implements Command {
    private View view;

    public SaveCommand(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            view.viewSave();
            System.out.println("Results saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving results: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        System.out.println("Undo not supported for SaveCommand.");
    }
}
