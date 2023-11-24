package use_case2.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData foundEvents);

    void prepareFailView(String error);
}
