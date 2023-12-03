package USE_CASE.add_event;



import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.Event;
import ENTITY.EventFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddEventInteractor implements AddEventInputBoundary{

    final LoadEventsDataAccessInterface eventDataAccessInterface;

    final AddEventOutputBoundary addEventPresenter;

    final EventFactory eventFactory;

    public AddEventInteractor(LoadEventsDataAccessInterface eventDataAccessInterface, AddEventOutputBoundary addEventPresenter, EventFactory eventFactory) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.addEventPresenter = addEventPresenter;
        this.eventFactory = eventFactory;

    }


    @Override
    public void execute(AddEventInputData addEventInputData) {
        if(addEventInputData.getEventName() == null){
           addEventPresenter.prepareFailView("Enter name of event, please");
        } else if(addEventInputData.getDescription() == null) {
            addEventPresenter.prepareFailView("Enter description of event, please");
        }else if(addEventInputData.getEventDate() == null){
            addEventPresenter.prepareFailView("Enter date,please");
        } else if(addEventInputData.getEventDate().isBefore(LocalDate.now())){
            addEventPresenter.prepareFailView("Enter right date, please");
        } else if (addEventInputData.getType() == null) {
            addEventPresenter.prepareFailView("Add time of event, please");
        } else {
            Event event = eventFactory.create(addEventInputData.getEventName(), addEventInputData.getType(), addEventInputData.getDescription(), addEventInputData.getEventDate(), addEventInputData.getEventTime(), addEventInputData.getCreator(), null, addEventInputData.getLatitude(), addEventInputData.getLongitude());
            eventDataAccessInterface.addEvent(event);

            AddEventOutputData signupOutputData = new AddEventOutputData(event.getEventName(), false);
            addEventPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
