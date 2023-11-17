package use_case.delete_event;

import java.util.Set;

public class DeleteEventInteractor implements DeleteEventInputBoundary {



    final DeleteEventDataAccessInterface eventDataAccessObject;
    final DeleteEventOutputBoundary deleteEventPresenter;

    public DeleteEventInteractor(DeleteEventDataAccessInterface eventDataAccessObject, DeleteEventOutputBoundary deleteEventPresenter) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.deleteEventPresenter = deleteEventPresenter;
    }


    @Override
    public void execute() {

       String deletedEvent = eventDataAccessObject.deletedEvent();
       eventDataAccessObject.deleteEvent();


        DeleteEventOutputData clearOutputData = new DeleteEventOutputData(deletedEvent,false);
        deleteEventPresenter.prepareSuccessCase(clearOutputData);
    }

}
