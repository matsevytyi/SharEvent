package VIEW_CREATOR;

import ENTITY.User;
import INTERFACE_ADAPTER.LoadMapState;
import USE_CASE.login.LoginOutputData;
import lombok.Getter;
import lombok.Setter;
import org.jxmapviewer.JXMapKit;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadMapViewModel {

    @Getter
    @Setter
    private JXMapKit mapKit;

    @Getter
    private String viewProfileButtonImgPath;
    @Getter
    private String filterEventsButtonImgPath;
    @Getter
    private String viewFriendsButtonImgPath;
    @Getter
    private String viewEventsButtonImgPath;
    @Getter
    private String addEventButtonImgPath;
    @Getter
    private String updateEventsButtonName;

    @Getter
    private String updateEventsButtonStyle;

    @Getter
    private String rightMenuButtonsStyle;

    @Getter
    private double rightMenuButtonSize;

    @Getter
    private String viewProfileButtonStyle;

    @Getter
    private double viewProfileButtonSize;

    @Getter
    @Setter
    private String API_error;

    @Getter
    @Setter
    private String Map_Load_Error;


    private LoadMapState state = new LoadMapState ();


    private String loggedInUser;

    private User loggedInUserObject;


    public LoadMapViewModel() {
        mapKit = new JXMapKit();

        viewProfileButtonImgPath = "/Users/innaboiko/Desktop/CSC207/boikoinn/boikoinn/SharEvent/resources/UI_elements/DefaultProfilePicture.png";
        filterEventsButtonImgPath = "/Users/innaboiko/Desktop/CSC207/boikoinn/boikoinn/SharEvent/resources/UI_elements/MapFiltersButton.png";
        viewFriendsButtonImgPath = "/Users/innaboiko/Desktop/CSC207/boikoinn/boikoinn/SharEvent/resources/UI_elements/ViewMyEventsButtonBg.png";
        viewEventsButtonImgPath = "/Users/innaboiko/Desktop/CSC207/boikoinn/boikoinn/SharEvent/resources/UI_elements/ViewFriendsButtonBg.png";
        addEventButtonImgPath = "/Users/innaboiko/Desktop/CSC207/boikoinn/boikoinn/SharEvent/resources/UI_elements/AddEventButtonBg.png";
        updateEventsButtonName = "Update Events";

        updateEventsButtonStyle = "-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;";


        rightMenuButtonsStyle = "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;";
        rightMenuButtonSize = 170;

        viewProfileButtonStyle = "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 100;";
        viewProfileButtonSize = 200;

        API_error = null;
        Map_Load_Error = null;

    }

    public void setState(LoadMapState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoadMapState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoginOutputData loggedInUser) {
        this.loggedInUser = loggedInUser.getUsername();
    }


    public void setLoggedInUserObject(User user) {
        loggedInUserObject = user;
    }

    public User getLoggedInUserObject() {
        return loggedInUserObject;
    }

    public String getViewName() {

        return "";
    }
}
