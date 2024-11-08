package tourism_app.menu.commands;

public class SelectMealCommand implements Command {
    public void execute() {
        System.out.println("Select meal command has been executed.");
    }

    public String getName(){
        return "Select meal";
    }
}
