package USE_CASE.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData foundEvents);

    void prepareFailView(String error);
}
