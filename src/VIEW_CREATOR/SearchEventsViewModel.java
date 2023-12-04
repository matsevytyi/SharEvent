package VIEW_CREATOR;

import lombok.Getter;

@Getter
public class SearchEventsViewModel {
    final String SEARCH_FRAME_TITLE;
    final String SEARCH_LABEL;
    final String APPLY_BUTTON_LABEL;
    final String CANCEL_BUTTON_LABEL;
    final int SEARCH_FIELD_SIZE;
    final float SEARCH_FIELD_FONT;
    final int SEARCH_FRAME_HEIGHT;
    final int SEARCH_FRAME_WIDTH;
    SearchEventsViewModel(){
        this.SEARCH_FRAME_TITLE = "Search";
        this.SEARCH_LABEL = "Search Events by name: ";
        this.APPLY_BUTTON_LABEL = "Apply";
        this.CANCEL_BUTTON_LABEL = "Cancel";
        this.SEARCH_FIELD_SIZE = 15;
        this.SEARCH_FIELD_FONT = 50f;
        this.SEARCH_FRAME_HEIGHT = 130;
        this.SEARCH_FRAME_WIDTH = 250;
    }
}
