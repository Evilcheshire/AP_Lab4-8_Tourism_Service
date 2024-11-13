package tourism_app.menu.commands;

import tourism_app.users.User;

public interface AuthCommand {
    public User getAuthenticatedUser();
    public void execute();
}
