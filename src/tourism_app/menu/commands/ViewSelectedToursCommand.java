package tourism_app.menu.commands;

public class ViewSelectedToursCommand implements Command {
    public void execute() {
        System.out.println("View selected tours command has been executed.");
    }

    public String getName(){
        return "View selected tours";
    }
}
