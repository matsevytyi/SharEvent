package APP;

import data_access.LoadEventsDataAccessInterface;
import entity.EventFactory;
import interface_adapter.add_event.AddEventController;
import interface_adapter.add_event.AddEventPresenter;
import interface_adapter.add_event.AddEventViewModel;
import interface_adapter.view_event.ViewEventController;
import interface_adapter.view_event.ViewEventPresenter;
import interface_adapter.view_event.ViewEventViewModel;
import use_case.add_event.AddEventInteractor;
import use_case.add_event.AddEventOutputBoundary;
import use_case.view_event.ViewEventInteractor;
import use_case.view_event.ViewEventOutputBoundary;
import view.LoadMapView;
import view.MapViewModel;
import view.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class MapUseCasesFactory {

    private MapUseCasesFactory(){};

    public static LoadMapView create(AddEventViewModel addEventViewModel, ViewEventViewModel viewEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel){
        AddEventController addEventController = null;
        try {
            addEventController = addEventUseCase(addEventViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ViewEventController viewEventController = null;
        try {
            viewEventController = viewEventUseCase(viewEventViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new LoadMapView(addEventViewModel,addEventController,viewEventViewModel, viewEventController);
    }



    private static AddEventController addEventUseCase(AddEventViewModel addEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterfac, ViewManagerModel viewManagerModel ) throws IOException {
       AddEventOutputBoundary addEventOutputBoundary= new AddEventPresenter(addEventViewModel, viewManagerModel);
        EventFactory eventFactory= new EventFactory();
        AddEventInteractor addEventInteractor = new AddEventInteractor(loadEventsDataAccessInterfac,addEventOutputBoundary, eventFactory );

        return new AddEventController(addEventInteractor);

    }

    private static ViewEventController viewEventUseCase(ViewEventViewModel viewEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterfac, ViewManagerModel viewManagerModel ) throws IOException {
        ViewEventOutputBoundary viewEventOutputBoundary= new ViewEventPresenter(viewEventViewModel, viewManagerModel);
       ViewEventInteractor viewEventInteractor = new ViewEventInteractor(loadEventsDataAccessInterfac,viewEventOutputBoundary);

        return new ViewEventController(viewEventInteractor);

    }
}
