package APP;

import data_access.LoadEventsDataAccessInterface;
import entity.EventFactory;
import interface_adapter.add_event.AddEventController;
import interface_adapter.add_event.AddEventPresenter;
import interface_adapter.add_event.AddEventViewModel;
import use_case.add_event.AddEventInteractor;
import use_case.add_event.AddEventOutputBoundary;
import view.LoadMapView;
import view.MapViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class MapUseCasesFactory {

    private MapUseCasesFactory(){};

    public static LoadMapView create(AddEventViewModel addEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel){
        AddEventController addEventController = null;
        try {
            addEventController = addEventUseCase(addEventViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new LoadMapView(addEventViewModel,addEventController);
    }



    private static AddEventController addEventUseCase(AddEventViewModel addEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterfac, ViewManagerModel viewManagerModel ) throws IOException {
       AddEventOutputBoundary addEventOutputBoundary= new AddEventPresenter(addEventViewModel, viewManagerModel);
        EventFactory eventFactory= new EventFactory();
        AddEventInteractor addEventInteractor = new AddEventInteractor(loadEventsDataAccessInterfac,addEventOutputBoundary, eventFactory );

        return new AddEventController(addEventInteractor);

    }
}
