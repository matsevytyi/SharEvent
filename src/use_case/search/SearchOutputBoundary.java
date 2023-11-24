package use_case.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData foundEvents);

    void prepareFailView(String error);
}
