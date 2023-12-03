package VIEW_CREATOR;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class LoadMapButtonFactory {

    public static Button createUpdateEventsButton(String buttonName, String buttonStyle) {
        Button updateEventsButton = new Button(buttonName);
        updateEventsButton.setStyle(buttonStyle);

        updateEventsButton.setPrefSize(400, 40);

        updateEventsButton.setVisible(false);

        return updateEventsButton;

    }

    public static Button createFilterEventsButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {

        Button filterEventsButton = new Button();
        filterEventsButton.setStyle(buttonStyle);

        filterEventsButton.setPrefSize(buttonSize, buttonSize);
        filterEventsButton.setMaxSize(buttonSize, buttonSize);

        try {
            ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

            buttonImage.setFitWidth(buttonSize - 30);
            buttonImage.setFitHeight(buttonSize - 30);

            buttonImage.setOpacity(0.7);

            filterEventsButton.setGraphic(buttonImage);
            filterEventsButton.setText("");
        } catch (Exception e) {
            System.out.println(e);
            filterEventsButton.setText("Filter Events");
        }

        return filterEventsButton;
    }

    public static Button createSearchEventsButton(Class<?> clazz, String path, String buttonStyle,double buttonSize) {

        Button searchEventsButton = new Button();
        searchEventsButton.setStyle(buttonStyle);

        searchEventsButton.setPrefSize(buttonSize, buttonSize);
        searchEventsButton.setMaxSize(buttonSize, buttonSize);

        try {
            ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

            buttonImage.setFitWidth(buttonSize - 30);
            buttonImage.setFitHeight(buttonSize - 35);

            buttonImage.setOpacity(0.85);

            searchEventsButton.setGraphic(buttonImage);
            searchEventsButton.setText("");
        } catch (Exception e) {
            System.out.println(e);
            searchEventsButton.setText("Search");
        }

        return searchEventsButton;
    }

    public static Button createViewEventsButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button viewEventsButton = new Button();
        viewEventsButton.setStyle(buttonStyle);

        viewEventsButton.setPrefSize(buttonSize, buttonSize);
        viewEventsButton.setMaxSize(buttonSize, buttonSize);

        try {
            ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

            buttonImage.setFitWidth(buttonSize - 30);
            buttonImage.setFitHeight(buttonSize - 30);

            buttonImage.setOpacity(0.7);

            viewEventsButton.setGraphic(buttonImage);
            viewEventsButton.setText("");
        } catch (Exception e) {
            System.out.println(e);
            viewEventsButton.setText("View Events");
        }



        return viewEventsButton;
    }

    public static Button createAddEventButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button addEventButton = new Button();

        addEventButton.setStyle(buttonStyle);

        addEventButton.setPrefSize(buttonSize, buttonSize);
        addEventButton.setMaxSize(buttonSize, buttonSize);

        try {
            ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

            buttonImage.setFitWidth(buttonSize - 35);
            buttonImage.setFitHeight(buttonSize - 35);

            buttonImage.setOpacity(0.7);

            addEventButton.setGraphic(buttonImage);
            addEventButton.setText("");
        } catch (Exception e) {
            System.out.println(e);
            addEventButton.setText("Add Event");
        }

        return addEventButton;
    }

    public static Button createViewProfileButton(Class<?> clazz, String path, String buttonStyle, double buttonSize) {
        Button viewProfileButton = new Button();
        viewProfileButton.setStyle(buttonStyle);

        viewProfileButton.setPrefSize(buttonSize, buttonSize);
        viewProfileButton.setMaxSize(buttonSize, buttonSize);

        try {
            ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

            buttonImage.setFitWidth(buttonSize + 70);
            buttonImage.setFitHeight(buttonSize + 70);

            buttonImage.setOpacity(0.7);

            viewProfileButton.setGraphic(buttonImage);
            viewProfileButton.setText("");
        } catch (Exception e) {
            System.out.println(e);
            viewProfileButton.setText("View Profile");
        }

        return viewProfileButton;
    }

}

