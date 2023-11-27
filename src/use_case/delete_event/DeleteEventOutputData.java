package use_case.delete_event;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteEventOutputData {


    private String deletedEvent;

    private boolean useCaseFailed;


    public DeleteEventOutputData(String deletedEvent, boolean useCaseFailed) {
        this.deletedEvent = deletedEvent;
        this.useCaseFailed = useCaseFailed;
    }


}
