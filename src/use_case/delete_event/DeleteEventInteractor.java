package use_case.delete_event;

import data_access.LoadEventsDataAccessInterface;

import java.util.Set;

public class DeleteEventInteractor implements DeleteEventInputBoundary {



    final LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    final DeleteEventOutputBoundary deleteEventPresenter;

    public DeleteEventInteractor(LoadEventsDataAccessInterface loadEventsDataAccessInterface, DeleteEventOutputBoundary deleteEventPresenter) {
        this.loadEventsDataAccessInterface = loadEventsDataAccessInterface;
        this.deleteEventPresenter = deleteEventPresenter;
    }


    @Override
    public void execute(DeleteEventInputData deleteEventInputData) {


      String deletedEventId = loadEventsDataAccessInterface.deleteEvent(deleteEventInputData.getEventId()).getEventName();

        DeleteEventOutputData deleteEventOutputData = new DeleteEventOutputData(deletedEventId,false);
        deleteEventPresenter.prepareSuccessCase(deleteEventOutputData);
    }

}
