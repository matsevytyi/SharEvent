package use_case.add_event;

public interface AddEventOutputBoundary {

    void prepareSuccessView(AddEventOutputData event);

    void prepareFailView(String error);
}
