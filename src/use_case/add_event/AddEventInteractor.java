package use_case.add_event;

import data_access.EventDataAccessInterface;
import entity.Event;
import entity.EventFactory;

public class AddEventInteractor implements AddEventInputBoundary{

    final EventDataAccessInterface eventDataAccessInterface;

    final AddEventOutputBoundary addEventPresenter;

    final EventFactory eventFactory;

    int eventId;
    User creator;

    public AddEventInteractor(EventDataAccessInterface eventDataAccessInterface, AddEventOutputBoundary addEventPresenter, EventFactory eventFactory) {
        this.eventDataAccessInterface = eventDataAccessInterface;
        this.addEventPresenter = addEventPresenter;
        this.eventFactory = eventFactory;
    }


    @Override
    public void execute(AddEventInputData addEventInputData) {

        //add conditions

        Event event =  eventFactory.create(eventId, addEventInputData.getEventName(), addEventInputData.getLatitude(), addEventInputData.getLongitude(), addEventInputData.getEventDate(), addEventInputData.getDescription(), creator);
        eventDataAccessInterface.addEvent(event);

        AddEventOutputData signupOutputData = new  AddEventOutputData(event.getEventName(),  false);
        addEventPresenter.prepareSuccessView(signupOutputData);

    }
}
