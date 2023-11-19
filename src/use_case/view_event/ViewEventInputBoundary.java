package use_case.view_event;


    public interface ViewEventInputBoundary {
        void viewEvent(int eventId);
        void registerForEvent(int eventId);
        void deleteEvent(int eventId);
    }

