package INTERFACE_ADAPTER.delete_event;


import USE_CASE.delete_event.DeleteEventInputBoundary;
import USE_CASE.delete_event.DeleteEventInputData;
/**
 * The class serves as the controller for the "delete event" use case.
 * It facilitates the communication between the external interface and the use case interactor.
 * This class receives input parameters related to the event ID and delegates the execution
 * of the use case to the provided DeleteEventInputBoundary instance.
 */
public class DeleteEventController {

    final DeleteEventInputBoundary deleteEventUseCaseInteractor;
    /**
     * Constructs a DeleteEventController with the specified  DeleteEventInputBoundary.
     * @param deleteEventUseCaseInteractor The use case interactor for the "delete event" functionality.
     */
    public DeleteEventController(DeleteEventInputBoundary deleteEventUseCaseInteractor) {
        this.deleteEventUseCaseInteractor = deleteEventUseCaseInteractor;
    }

    /**
     * This method executes the "delete event" use case with the provided event ID.
     * @param eventId The ID of the event to be deleted.
     */
    public void execute(int eventId){
        DeleteEventInputData deleteEventInputData= new DeleteEventInputData(eventId);
        deleteEventUseCaseInteractor.execute(deleteEventInputData);
    }
}
