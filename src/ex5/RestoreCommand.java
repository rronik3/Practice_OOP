package ex5;

import ex3.View;

public class RestoreCommand implements Command {
    private View view;

    public RestoreCommand(View view) {
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            view.viewRestore();
            System.out.println("Results restored successfully.");
        } catch (Exception e) {
            System.out.println("Error restoring results: " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        System.out.println("Undo not supported for RestoreCommand.");
    }
}
