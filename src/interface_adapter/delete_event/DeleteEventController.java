package interface_adapter.delete_event;

import use_case.delete_event.DeleteEventInputBoundary;
import use_case.delete_event.DeleteEventInputData;

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
