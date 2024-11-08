package tourism_app.menu.commands;

public class EditTourCommand implements Command {
    public void execute() {
        System.out.println("Edit tour command has been executed.");
    }

    public String getName(){
        return "Edit tour";
    }
}
