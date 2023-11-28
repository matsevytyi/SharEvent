package INTERFACE_ADAPTER.delete_event;


import USE_CASE.delete_event.DeleteEventInputBoundary;
import USE_CASE.delete_event.DeleteEventInputData;

public class DeleteEventController {

    final DeleteEventInputBoundary deleteEventUseCaseInteractor;

    public DeleteEventController(DeleteEventInputBoundary deleteEventUseCaseInteractor) {
        this.deleteEventUseCaseInteractor = deleteEventUseCaseInteractor;
    }


    public void execute(int eventId){
        DeleteEventInputData deleteEventInputData= new DeleteEventInputData(eventId);
        deleteEventUseCaseInteractor.execute(deleteEventInputData);
    }
}
