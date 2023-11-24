package use_case2.search;

import java.util.ArrayList;

public class SearchOutputData implements SearchOutputBoundary{
    private final ArrayList<EventInterface> foundEvents;

    private boolean useCaseFailed;

    public SearchOutputData(ArrayList<EventInterface> foundEvents, boolean useCaseFailed) {
        this.foundEvents = foundEvents;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<EventInterface> getFoundEvents() {return foundEvents;}

}
