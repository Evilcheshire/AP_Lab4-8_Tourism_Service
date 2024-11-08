package tourism_app.menu.commands;

public class EditUserInfoCommand implements Command {

    public void execute() {
        System.out.println("Edit user information command has been executed.");
    }

    public String getName(){
        return "Edit information";
    }
}
