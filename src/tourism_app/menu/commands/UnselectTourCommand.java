package tourism_app.menu.commands;

public class UnselectTourCommand implements Command {
    public void execute() {
        System.out.println("Unselect tours command has been executed.");
    }

    public String getName(){
        return "Unselect tour";
    }
}
