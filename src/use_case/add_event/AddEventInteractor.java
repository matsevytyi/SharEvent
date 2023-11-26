package use_case.add_event;


import data_access.LoadEventsDataAccessInterface;
import entity.Event;
import entity.EventFactory;
import entity.User;

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


        Event event = eventFactory.create(addEventInputData.getEventName(), addEventInputData.getType(), addEventInputData.getDescription(),  addEventInputData.getEventDate(), addEventInputData.getEventTime(),  addEventInputData.getCreator(), null, addEventInputData.getLatitude(), addEventInputData.getLongitude());
      eventDataAccessInterface.addEvent(event);

        AddEventOutputData signupOutputData = new  AddEventOutputData(event.getEventName(),  false);
        addEventPresenter.prepareSuccessView(signupOutputData);

    }
}
