package VIEW_CREATOR;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jxmapviewer.JXMapKit;

public class LoadMapButtonFactory {

    public static Button createUpdateEventsButton(String buttonName, String buttonStyle) {
        Button updateEventsButton = new Button(buttonName);
        updateEventsButton.setStyle(buttonStyle);

        updateEventsButton.setPrefSize(400, 40);

        //Move setOnAction to another class due to Clean Architecture Principles

        updateEventsButton.setVisible(false);

        return updateEventsButton;

    }

    public static Button createFilterEventsButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {

        Button filterEventsButton = new Button();
        filterEventsButton.setStyle(buttonStyle);

//        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));
//
//        buttonImage.setFitWidth(buttonSize - 15);
//        buttonImage.setFitHeight(buttonSize - 15);

        filterEventsButton.setPrefSize(buttonSize, buttonSize);
        filterEventsButton.setMaxSize(buttonSize, buttonSize);

//        filterEventsButton.setGraphic(buttonImage);

        return filterEventsButton;
    }

    //I need to do for this button the same I did for createViewEventsButton

    public static Button createViewFriendsButton(Class<?> clazz, String path, String buttonStyle,double buttonSize) {

        Button viewFriendsButton = new Button();
        viewFriendsButton.setStyle(buttonStyle);

//        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

//        buttonImage.setFitWidth(buttonSize - 30);
//        buttonImage.setFitHeight(buttonSize - 35);

        viewFriendsButton.setPrefSize(buttonSize, buttonSize);
        viewFriendsButton.setMaxSize(buttonSize, buttonSize);

//        viewFriendsButton.setGraphic(buttonImage);

        return viewFriendsButton;
    }

    public static Button createViewEventsButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button viewEventsButton = new Button();
        viewEventsButton.setStyle(buttonStyle);

//        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));
//
//        buttonImage.setFitWidth(buttonSize - 35);
//        buttonImage.setFitHeight(buttonSize - 35);

//        buttonImage.setOpacity(0.7);

        viewEventsButton.setPrefSize(buttonSize, buttonSize);
        viewEventsButton.setMaxSize(buttonSize, buttonSize);

//        viewEventsButton.setGraphic(buttonImage);

        return viewEventsButton;
    }

    public static Button createAddEventButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button addEventButton = new Button();
        addEventButton.setStyle(buttonStyle);

//        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

//        buttonImage.setFitWidth(buttonSize - 35);
//        buttonImage.setFitHeight(buttonSize - 35);
//
//        buttonImage.setOpacity(0.7);

        addEventButton.setPrefSize(buttonSize, buttonSize);
        addEventButton.setMaxSize(buttonSize, buttonSize);

//        addEventButton.setGraphic(buttonImage);

        return addEventButton;
    }

    public static Button createViewProfileButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button viewProfileButton = new Button();
        viewProfileButton.setStyle(buttonStyle);


//        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

//        buttonImage.setFitWidth(buttonSize + 70);
//        buttonImage.setFitHeight(buttonSize + 70);

        viewProfileButton.setPrefSize(buttonSize, buttonSize);
        viewProfileButton.setMaxSize(buttonSize, buttonSize);

//        viewProfileButton.setGraphic(buttonImage);

        return viewProfileButton;
    }

}

