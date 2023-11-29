package USE_CASE.filter;

public interface FilterOutputBoundary {

    void prepareSuccessView(FilterOutputData foundEvents);

    void prepareFailView(String error);
}
