package USE_CASE.delete_event;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteEventOutputData {


    private String deletedEvent;

    private boolean useCaseFailed;

    /**
     * Constructs a DeleteEventOutputData with the specified deleted event information and use case failure flag.
     * @param deletedEvent    The name or identifier of the deleted event.
     * @param useCaseFailed   A flag indicating whether the use case execution has failed.
     */
    public DeleteEventOutputData(String deletedEvent, boolean useCaseFailed) {
        this.deletedEvent = deletedEvent;
        this.useCaseFailed = useCaseFailed;
    }


}
