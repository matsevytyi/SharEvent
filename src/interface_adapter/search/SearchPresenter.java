package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final LoadMapViewModel loadMapViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(ViewManagerModel viewManagerModel,
                           LoadMapViewModel loadMapViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.LoadMapViewModel = loadMapViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
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
