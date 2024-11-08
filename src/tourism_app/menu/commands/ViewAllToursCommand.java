package tourism_app.menu.commands;

public class ViewAllToursCommand implements Command {
    public void execute() {
        System.out.println("View all tours command has been executed.");
    }

    public String getName(){
        return "View all tours";
    }
}
