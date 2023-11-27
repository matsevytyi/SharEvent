package APP;

import INTERFACE_ADAPTER.ViewManagerModel;
import INTERFACE_ADAPTER.login_adapter.LoginViewModel;
import INTERFACE_ADAPTER.logout_adapter.LogOutController;
import INTERFACE_ADAPTER.logout_adapter.LogOutPresenter;
import USE_CASE.logout.LogOutInteractor;
import VIEW.LoadMapView;
import VIEW_CREATOR.LoadMapViewModel;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

//    public static LoadMapView create(
//            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, LoadMapViewModel loggedInViewModel) { //ClearInputBoundary clearInputBoundary) {
//
//
//        LogOutPresenter logoutPresenter = new LogOutPresenter (loginViewModel, viewManagerModel);
//
//        LogOutInteractor logoutInteractor = new LogOutInteractor(logoutPresenter);
//
//
//        LogOutController logoutController = new LogOutController (logoutInteractor);
//        // LoggedController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
//
//        return new LoadMapView(loggedInViewModel);
//
//    }
}
