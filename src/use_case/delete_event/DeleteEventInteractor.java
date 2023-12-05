package USE_CASE.delete_event;


import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;

public class DeleteEventInteractor implements DeleteEventInputBoundary {



    final LoadEventsDataAccessInterface loadEventsDataAccessInterface;
    final DeleteEventOutputBoundary deleteEventPresenter;
    /**
     * Constructs a DeleteEventInteractor with the specified data access interface and output boundary.
     * @param loadEventsDataAccessInterface The data access interface for loading events.
     * @param deleteEventPresenter         The output boundary (presenter) for handling the result of the delete event.
     */
    public DeleteEventInteractor(LoadEventsDataAccessInterface loadEventsDataAccessInterface, DeleteEventOutputBoundary deleteEventPresenter) {
        this.loadEventsDataAccessInterface = loadEventsDataAccessInterface;
        this.deleteEventPresenter = deleteEventPresenter;
    }

    /**
     * Executes the deletion of an event based on the provided Input Data}.
     * @param deleteEventInputData The input data containing information about the event to be deleted.
     */
    @Override
    public void execute(DeleteEventInputData deleteEventInputData) {

        String deletedEventId = loadEventsDataAccessInterface.getEventById(deleteEventInputData.getEventId());
        loadEventsDataAccessInterface.deleteEvent(deleteEventInputData.getEventId());

        DeleteEventOutputData deleteEventOutputData = new DeleteEventOutputData(deletedEventId,false);
        deleteEventPresenter.prepareSuccessCase(deleteEventOutputData);
    }

}
