package tourism_app.menu.commands;

public class ExitCommand implements Command {
    public void execute() {
        System.out.println("Exit command has been executed.");
    }

    public String getName(){
        return "Exit";
    }
}
