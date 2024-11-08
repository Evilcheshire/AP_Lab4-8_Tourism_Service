package tourism_app.menu.commands;

public class SelectLocationsCommand implements Command {
    public void execute() {
        System.out.println("Select locations command has been executed.");
    }

    public String getName(){
        return "Select locations user";
    }
}
