package use_case.filter;

import use_case.search.SearchInputData;

public interface FilterInputBoundary {
    void execute(FilterInputData filterInputData);
}
