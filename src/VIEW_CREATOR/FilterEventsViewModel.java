package VIEW_CREATOR;

import lombok.Getter;

@Getter
public class FilterEventsViewModel {
    final String FILTER_FRAME_TITLE;
    final int VERTICAL_GAP;
    final int FRAME_HEIGHT;
    final int FRAME_WIDTH;
    final String APPLY_BUTTON_TITLE;
    final String CANCEL_BUTTON_TITLE;
    final String LABEL_TEXT;

    final String TYPE1;
    final String TYPE2;
    final String TYPE3;
    final String TYPE4;
    final String TYPE5;
    final String TYPE6;
    final String TYPE7;
    final String TYPE8;

    FilterEventsViewModel(){
        this.FILTER_FRAME_TITLE = "Filter";
        this.VERTICAL_GAP = 15;
        this.FRAME_HEIGHT = 300;
        this.FRAME_WIDTH = 250;
        this.APPLY_BUTTON_TITLE = "Apply";
        this.CANCEL_BUTTON_TITLE = "Cancel";
        this.LABEL_TEXT = "Filter by Type: ";

        this.TYPE1 = "Sports and Fitness";
        this.TYPE2 = "Music";
        this.TYPE3 = "Food and Drinks";
        this.TYPE4 = "Gaming";
        this.TYPE5 = "Education and Learning";
        this.TYPE6 = "Outdoors and Adventure";
        this.TYPE7 = "Other";
        this.TYPE8 = "Show All";
    }


}
