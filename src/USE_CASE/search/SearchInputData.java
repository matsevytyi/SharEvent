package USE_CASE.search;

import ENTITY.EventInterface;

import java.util.ArrayList;

public class SearchInputData {

    final private String searchInput;


    public SearchInputData(String searchInput) {
        this.searchInput = searchInput;
    }

    String getSearchInput() {
        return this.searchInput;
    }

}
