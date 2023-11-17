package interface_adapter.delete_event;

public class DeleteEventState {

    public String getDeletedEvent() {
        return deletedEvent;
    }

    public void setDeletedEvent(String deletedEvent) {
        this.deletedEvent = deletedEvent;
    }

    public String getDeletedEventError() {
        return deletedEventError;
    }

    public void setDeletedEventError(String deletedEventError) {
        this.deletedEventError = deletedEventError;
    }

    private String deletedEvent = "";
    private String deletedEventError = "";


    public DeleteEventState(String deletedEvent, String deletedEventError) {
        this.deletedEvent = deletedEvent;
        this.deletedEventError = deletedEventError;
    }


}
