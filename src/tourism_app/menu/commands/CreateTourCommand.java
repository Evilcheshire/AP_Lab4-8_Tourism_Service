package tourism_app.menu.commands;

import tourism_app.lib.TourDatabase;

public class CreateTourCommand implements Command {
    private final TourDatabase tourDatabase;

    public CreateTourCommand(TourDatabase tourDatabase){
        this.tourDatabase = tourDatabase;
    }

    public void execute() {
        System.out.println("Create tour command has been executed.");
    }

    public String getName() {
        return "Create tour";
    }
}
