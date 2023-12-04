package INTERFACE_ADAPTER.delete_event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * The {@code DeleteEventState} class encapsulates the state information for the "delete event" functionality.
 * It includes properties such as the name of the deleted event, error messages, deleted event ID, and ID error messages.
 */
@Getter @AllArgsConstructor @Setter
public class DeleteEventState {

    private String deletedEventName = "";
    private String deletedEventError = "";

    private int deletedEventId= 0;
    private String deletedEventIdError = "";
   public DeleteEventState(){}


}
