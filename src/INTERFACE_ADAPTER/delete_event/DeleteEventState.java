package INTERFACE_ADAPTER.delete_event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @AllArgsConstructor @Setter
public class DeleteEventState {

    private String deletedEventName = "";
    private String deletedEventError = "";

    private int deletedEventId= 0;
    private String deletedEventIdError = "";
   public DeleteEventState(){}


}
