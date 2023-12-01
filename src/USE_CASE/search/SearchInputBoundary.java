package USE_CASE.search;

public interface SearchInputBoundary {
    void execute(SearchInputData searchInputData);

    void executeShowAll(SearchShowAllData searchShowAllData);
}
