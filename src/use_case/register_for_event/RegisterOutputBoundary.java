package USE_CASE.register_for_event;

import USE_CASE.delete_event.DeleteEventOutputData;

public interface RegisterOutputBoundary {
    void prepareSuccessCase();

    public void prepareFailCase(String error);
}
