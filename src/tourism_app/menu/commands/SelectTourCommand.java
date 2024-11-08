package tourism_app.menu.commands;

public class SelectTourCommand implements Command {
    public void execute() {
        System.out.println("Select tour command has been executed.");
    }

    public String getName(){
        return "Select tour";
    }
}
