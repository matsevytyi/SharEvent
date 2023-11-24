package use_case.filter;

import use_case.search.SearchOutputData;
public interface FilterOutputBoundary {

    void prepareSuccessView(FilterOutputData foundEvents);

    void prepareFailView(String error);
}
