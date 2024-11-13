package tourism_app.users;

import tourism_app.tour.Tour;

import java.util.List;

public class UserWithTours {
    private User user;
    private List<Tour> selectedTours;

    public UserWithTours(User user, List<Tour> selectedTours) {
        this.user = user;
        this.selectedTours = selectedTours;
    }

    public User getUser() {
        return user;
    }

    public List<Tour> getSelectedTours() {
        return selectedTours;
    }

    public void addTour(Tour tour) {
        this.selectedTours.add(tour);
    }

    public void removeTour(Tour tour) {
        this.selectedTours.remove(tour);
    }
}
