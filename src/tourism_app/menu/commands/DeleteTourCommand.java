package tourism_app.menu.commands;

public class DeleteTourCommand implements Command {
    public void execute() {
        System.out.println("Delete tour command has been executed.");
    }

    public String getName(){
        return "Delete tour";
    }
}
