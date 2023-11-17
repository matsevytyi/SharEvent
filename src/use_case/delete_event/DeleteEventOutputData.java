package use_case.delete_event;

public class DeleteEventOutputData {

    public String getDeletedEvent() {
        return deletedEvent;
    }

    public void setDeletedEvent(String deletedEvent) {
        this.deletedEvent = deletedEvent;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    private String deletedEvent;

    private boolean useCaseFailed;


    public DeleteEventOutputData(String deletedEvent, boolean useCaseFailed) {
        this.deletedEvent = deletedEvent;
        this.useCaseFailed = useCaseFailed;
    }


}
