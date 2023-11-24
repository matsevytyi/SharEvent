package use_case2.filter;

import use_case.search.SearchInputData;

public interface FilterInputBoundary {
    void execute(FilterInputData filterInputData);
}
