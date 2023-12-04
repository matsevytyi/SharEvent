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
            setImage(filterEventsButton, clazz, path, -30);
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
            setImage(searchEventsButton, clazz, path, -30);
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
            setImage(viewEventsButton, clazz, path, -30);
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
            setImage(addEventButton, clazz, path, -35);
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
            setImage(viewProfileButton, clazz, path, 70);
        } catch (Exception e) {
            System.out.println(e);
            viewProfileButton.setText("View Profile");
        }

        return viewProfileButton;
    }

    private static void setImage(Button button, Class<?> clazz, String path, double limits) throws Exception {
        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(button.getWidth() + limits);
        buttonImage.setFitHeight(button.getHeight() + limits);

        buttonImage.setOpacity(0.7);

        button.setGraphic(buttonImage);
        button.setText("");
    }

}

