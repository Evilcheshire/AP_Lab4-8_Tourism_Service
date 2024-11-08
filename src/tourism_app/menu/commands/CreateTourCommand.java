package tourism_app.menu.commands;

public class CreateTourCommand implements Command {
    public void execute() {
        System.out.println("Create tour command has been executed.");
    }

    public String getName() {
        return "Create tour";
    }
}
