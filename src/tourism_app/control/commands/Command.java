package tourism_app.control.commands;

import tourism_app.users.User;

public interface Command {
    public void execute();
    public String getName();

    default User getAuthUser() {
        return null;
    }
}
