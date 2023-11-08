package use_case.add_event;

import entity.Event;
import entity.EventFactory;

public class AddEventInteractor implements AddEventInputBoundary{

    final AddEventDataAccessInterface addEventDataAccessInterface;

    final AddEventOutputBoundary addEventPresenter;

    final EventFactory eventFactory;

    int eventId;
    User creator;

    public AddEventInteractor(AddEventDataAccessInterface addEventDataAccessInterface, AddEventOutputBoundary addEventPresenter, EventFactory eventFactory) {
        this.addEventDataAccessInterface = addEventDataAccessInterface;
        this.addEventPresenter = addEventPresenter;
        this.eventFactory = eventFactory;
    }


    @Override
    public void execute(AddEventInputData addEventInputData) {

        //add conditions

        Event event =  eventFactory.create(eventId, addEventInputData.getEventName(), addEventInputData.getLatitude(), addEventInputData.getLongtitude(), addEventInputData.getEventDate(), addEventInputData.getDescription(), creator);
        addEventDataAccessInterface.save(event);

        AddEventOutputData signupOutputData = new  AddEventOutputData(event.getEventName(),  false);
        addEventPresenter.prepareSuccessView(signupOutputData);

    }
}
