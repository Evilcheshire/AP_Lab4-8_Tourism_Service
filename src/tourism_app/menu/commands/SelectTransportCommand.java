package tourism_app.menu.commands;

public class SelectTransportCommand implements Command {
    public void execute() {
        System.out.println("Select transport command has been executed.");
    }

    public String getName(){
        return "Select transport";
    }
}
