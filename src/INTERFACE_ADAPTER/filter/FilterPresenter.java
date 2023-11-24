package INTERFACE_ADAPTER.filter;

import USE_CASE.filter.FilterOutputData;

public class FilterPresenter {
    private final LoadMapViewModel loadMapViewModel;
    private ViewManagerModel viewManagerModel;

    public FilterPresenter(ViewManagerModel viewManagerModel,
                           LoadMapViewModel loadMapViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.LoadMapViewModel = loadMapViewModel;
    }

    @Override
    public void prepareSuccessView(FilterOutputData response) {
        // TODO
//        // On success, switch to the login view.
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
//
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO
//        SignupState signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
    }
}
}
