package use_case.filter;

import java.util.ArrayList;

public class FilterInputData {

    final private String typeInput;

    final private String timeInput;
    final private ArrayList<EventInterface> events;

    public FilterInputData(String typeInput,String timeInput, ArrayList<EventInterface> events) {
        this.typeInput = typeInput;
        this.timeInput = timeInput;
        this.events = events;
    }

    String getTypeInput() {
        return this.typeInput;
    }

    String getTimeInput() {
        return this.timeInput;
    }

    ArrayList<EventInterface> getEvents() {
        return this.events;
    }
}
