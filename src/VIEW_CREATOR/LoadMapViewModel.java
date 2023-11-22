package VIEW_CREATOR;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import org.jxmapviewer.JXMapKit;

import java.util.LinkedList;

public class LoadMapViewModel {

    @Getter
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



    public LoadMapViewModel() {
        mapKit = new JXMapKit();

        viewProfileButtonImgPath = "/UI_elements/DefaultProfilePicture.png";
        filterEventsButtonImgPath = "/UI_elements/MapFiltersButton.png";
        viewFriendsButtonImgPath = "/UI_elements/ViewFriendsButtonBg.png";
        viewEventsButtonImgPath = "/UI_elements/ViewMyEventsButtonBg.png";
        addEventButtonImgPath = "/UI_elements/AddEventButtonBg.png";
        updateEventsButtonName = "Update Events";

        updateEventsButtonStyle = "-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;";


        rightMenuButtonsStyle = "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;";
        rightMenuButtonSize = 170;

        viewProfileButtonStyle = "-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 100;";
        viewProfileButtonSize = 200;

    }
}
