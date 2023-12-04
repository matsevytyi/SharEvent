package USE_CASE.delete_event;

public interface DeleteEventOutputBoundary {

    void prepareSuccessCase(DeleteEventOutputData event);

    void prepareFailCase(String error);
}
