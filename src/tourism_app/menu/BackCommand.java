package tourism_app.menu;

import tourism_app.menu.commands.Command;

public class BackCommand implements Command {

    @Override
    public void execute(){}

    @Override
    public String getName(){
        return "Back";
    }
}
