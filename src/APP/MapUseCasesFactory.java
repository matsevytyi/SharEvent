package APP;


import DATA_ACCESS.loadevents_dataaccess.LoadEventsDataAccessInterface;
import ENTITY.EventFactory;
import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.add_event.AddEventController;
import INTERFACE_ADAPTER.add_event.AddEventPresenter;
import INTERFACE_ADAPTER.add_event.AddEventViewModel;
import INTERFACE_ADAPTER.delete_event.DeleteEventController;
import INTERFACE_ADAPTER.delete_event.DeleteEventPresenter;
import INTERFACE_ADAPTER.delete_event.DeleteEventViewModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.logout_adapter.LogOutController;
import INTERFACE_ADAPTER.logout_adapter.LogOutPresenter;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventPresenter;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import USE_CASE.add_event.AddEventInteractor;
import USE_CASE.add_event.AddEventOutputBoundary;
import USE_CASE.delete_event.DeleteEventInteractor;
import USE_CASE.delete_event.DeleteEventOutputBoundary;
import USE_CASE.logout.LogOutInteractor;
import USE_CASE.view_event.ViewEventInteractor;
import USE_CASE.view_event.ViewEventOutputBoundary;
import VIEW.LoadMapView;

import VIEW_CREATOR.LoadMapViewModel;

import java.io.IOException;

public class MapUseCasesFactory {

    private MapUseCasesFactory(){};

    public static LoadMapView create(LoadMapViewModel loadMapViewModel, LoginViewModel loginViewModel, AddEventViewModel addEventViewModel, ViewEventViewModel viewEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel, DeleteEventViewModel deleteEventViewModel){
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
       DeleteEventController deleteEventController = null;
        try {
           deleteEventController = deleteEventUseCase(deleteEventViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogOutPresenter logoutPresenter = new LogOutPresenter (loginViewModel, viewManagerModel);

        LogOutInteractor logoutInteractor = new LogOutInteractor(logoutPresenter);


        LogOutController logoutController = new LogOutController (logoutInteractor);
        return new LoadMapView(loadMapViewModel, addEventViewModel,addEventController,viewEventViewModel, viewEventController,  deleteEventViewModel, deleteEventController);
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

    private static DeleteEventController deleteEventUseCase(DeleteEventViewModel deleteEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel ) throws IOException {
        DeleteEventOutputBoundary deleteEventOutputBoundary = new DeleteEventPresenter(deleteEventViewModel, viewManagerModel);
        DeleteEventInteractor deleteEventInteractor = new DeleteEventInteractor(loadEventsDataAccessInterface, deleteEventOutputBoundary);

        return new DeleteEventController(deleteEventInteractor);

    }
}
