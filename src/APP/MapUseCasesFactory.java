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
import INTERFACE_ADAPTER.register_for_event.RegisterController;
import INTERFACE_ADAPTER.register_for_event.RegisterPresenter;
import INTERFACE_ADAPTER.register_for_event.RegisterViewModel;
import INTERFACE_ADAPTER.view_event.ViewEventController;
import INTERFACE_ADAPTER.view_event.ViewEventPresenter;
import INTERFACE_ADAPTER.view_event.ViewEventViewModel;
import INTERFACE_ADAPTER.view_profile.ViewProfileController;
import INTERFACE_ADAPTER.view_profile.ViewProfilePresenter;
import INTERFACE_ADAPTER.view_profile.ViewProfileState;
import INTERFACE_ADAPTER.view_profile.ViewProfileViewModel;
import USE_CASE.add_event.AddEventInteractor;
import USE_CASE.add_event.AddEventOutputBoundary;
import USE_CASE.delete_event.DeleteEventInteractor;
import USE_CASE.delete_event.DeleteEventOutputBoundary;
import USE_CASE.logout.LogOutInteractor;
import USE_CASE.register_for_event.RegisterInteractor;
import USE_CASE.register_for_event.RegisterOutputBoundary;
import USE_CASE.view_event.ViewEventInteractor;
import USE_CASE.view_event.ViewEventOutputBoundary;
import USE_CASE.view_profile.ViewProfileInteractor;
import USE_CASE.view_profile.ViewProfileOutputBoundary;
import VIEW.LoadMapView;

import VIEW_CREATOR.LoadMapViewModel;

import java.io.IOException;

public class MapUseCasesFactory {

    private MapUseCasesFactory(){};

    public static LoadMapView create(LoadMapViewModel loadMapViewModel, LoginViewModel loginViewModel, AddEventViewModel addEventViewModel, ViewEventViewModel viewEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel, DeleteEventViewModel deleteEventViewModel, RegisterViewModel registerViewModel, ViewProfileViewModel viewProfileViewModel){
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

         RegisterController registerController= null;

        try {
           registerController = registerUseCase(registerViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LogOutController logoutController = new LogOutController (logoutInteractor);

        ViewProfileController viewProfileController = null;

        try {
            viewProfileController = viewProfileUseCase(viewProfileViewModel, loadEventsDataAccessInterface, viewManagerModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new LoadMapView(loadMapViewModel, addEventViewModel,addEventController,viewEventViewModel, viewEventController,  deleteEventViewModel, deleteEventController, registerController, viewProfileViewModel, viewProfileController);
    }



    private static AddEventController addEventUseCase(AddEventViewModel addEventViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterfac, ViewManagerModel viewManagerModel ) throws IOException {
       AddEventOutputBoundary addEventOutputBoundary= new AddEventPresenter(addEventViewModel, viewManagerModel);
        EventFactory eventFactory= new EventFactory();
        AddEventInteractor addEventInteractor = new AddEventInteractor(loadEventsDataAccessInterfac,addEventOutputBoundary, eventFactory );

        return new AddEventController(addEventInteractor);

    }

    private static RegisterController registerUseCase(RegisterViewModel registerViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterfac, ViewManagerModel viewManagerModel ) throws IOException {
        RegisterOutputBoundary registerOutputBoundary = new RegisterPresenter(registerViewModel, viewManagerModel);

      RegisterInteractor registerInteractor = new RegisterInteractor(loadEventsDataAccessInterfac,registerOutputBoundary );

        return new RegisterController(registerInteractor);

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

    private static ViewProfileController viewProfileUseCase(ViewProfileViewModel viewProfileViewModel, LoadEventsDataAccessInterface loadEventsDataAccessInterface, ViewManagerModel viewManagerModel ) throws IOException {
        ViewProfileOutputBoundary viewProfileOutputBoundary = new ViewProfilePresenter(viewProfileViewModel, viewManagerModel);
        ViewProfileInteractor viewProfileInteractor = new ViewProfileInteractor(loadEventsDataAccessInterface, viewProfileOutputBoundary);

        return new ViewProfileController(viewProfileInteractor);

    }
}
