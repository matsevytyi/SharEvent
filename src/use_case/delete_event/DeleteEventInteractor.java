package USE_CASE.delete_event;

import DATA_ACCESS.LoadEventsDataAccessInterface;

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
