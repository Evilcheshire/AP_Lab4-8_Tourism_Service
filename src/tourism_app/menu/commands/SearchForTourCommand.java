package tourism_app.menu.commands;

public class SearchForTourCommand implements Command {
    public void execute() {
        System.out.println("Search for tour command has been executed.");
    }

    public String getName(){
        return "Search for tour";
    }
}
