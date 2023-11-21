package Entities;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jxmapviewer.JXMapKit;

public class ButtonFactory {

    //TODO:
    //Leave this transformation for onActionCkucked...

    public static Button createUpdateEventsButton(JXMapKit mapKit) {
        Button updateEventsButton = new Button("Update Events");
        updateEventsButton.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial'; -fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 30;");

        updateEventsButton.setPrefSize(400, 40);

        //Move setOnAction to another class due to Clean Architecture Principles
        updateEventsButton.setOnAction(e -> {
            System.out.println("Load Events in this area");
            mapKit.setCenterPosition(mapKit.getAddressLocation());
        });
        updateEventsButton.setVisible(false);

        return updateEventsButton;

    }

    public static Button createFilterEventsButton(Class<?> clazz, JXMapKit mapKit, String path, double buttonSize) {

        Button filterEventsButton = new Button();
        filterEventsButton.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;");

        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(buttonSize - 15);
        buttonImage.setFitHeight(buttonSize - 15);

        filterEventsButton.setPrefSize(buttonSize, buttonSize);
        filterEventsButton.setMaxSize(buttonSize, buttonSize);

        filterEventsButton.setGraphic(buttonImage);

        return filterEventsButton;
    }

    //I need to do for this button the same I did for createViewEventsButton

    public static Button createViewFriendsButton(Class<?> clazz, JXMapKit mapKit, String path, double buttonSize) {

        Button viewFriendsButton = new Button();
        viewFriendsButton.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;");

        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(buttonSize - 30);
        buttonImage.setFitHeight(buttonSize - 35);

        viewFriendsButton.setPrefSize(buttonSize, buttonSize);
        viewFriendsButton.setMaxSize(buttonSize, buttonSize);

        viewFriendsButton.setGraphic(buttonImage);

        return viewFriendsButton;
    }

    public static Button createViewEventsButton(Class<?> clazz, JXMapKit mapKit, String path, double buttonSize) {
        Button viewEventsButton = new Button();
        viewEventsButton.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;");

        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(buttonSize - 35);
        buttonImage.setFitHeight(buttonSize - 35);

        buttonImage.setOpacity(0.7);

        viewEventsButton.setPrefSize(buttonSize, buttonSize);
        viewEventsButton.setMaxSize(buttonSize, buttonSize);

        viewEventsButton.setGraphic(buttonImage);

        return viewEventsButton;
    }

    public static Button createAddEventButton(Class<?> clazz, JXMapKit mapKit, String path, double buttonSize) {
        Button addEventButton = new Button();
        addEventButton.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 40;");

        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(buttonSize - 35);
        buttonImage.setFitHeight(buttonSize - 35);

        buttonImage.setOpacity(0.7);

        addEventButton.setPrefSize(buttonSize, buttonSize);
        addEventButton.setMaxSize(buttonSize, buttonSize);

        addEventButton.setGraphic(buttonImage);

        return addEventButton;
    }

    public static Button createViewProfileButton(Class<?> clazz, JXMapKit mapKit, String path, double buttonSize) {
        Button viewProfileButton = new Button();
        viewProfileButton.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 100;");


        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(buttonSize + 70);
        buttonImage.setFitHeight(buttonSize + 70);

        viewProfileButton.setPrefSize(buttonSize, buttonSize);
        viewProfileButton.setMaxSize(buttonSize, buttonSize);

        viewProfileButton.setGraphic(buttonImage);

        return viewProfileButton;
    }

}

