package USE_CASE;

import VIEW_CREATOR.LoadMapViewModel;

public interface LoadMapInputBoundary {
    public void execute(LoadMapViewModel viewModel);

    public void viewProfile();

    public void filterEvents();

    public void viewFriends();

    public void viewEvents();

    public void addEvent();

    public void updateEvents();
}
