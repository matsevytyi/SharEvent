package VIEW_CREATOR;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public class LoadMapButtonFactory {

    /**
     * Creates an update events button with the specified name and style.
     *
     * @param  buttonName   the name of the button
     * @param  buttonStyle  the style of the button
     * @return              the created update events button
     */
    public static Button createUpdateEventsButton(String buttonName, String buttonStyle) {
        Button updateEventsButton = new Button(buttonName);
        updateEventsButton.setStyle(buttonStyle);

        updateEventsButton.setPrefSize(400, 40);

        updateEventsButton.setVisible(false);

        return updateEventsButton;

    }

    /**
     * Creates a filter events button with the specified class, path, button style, and button size.
     *
     * Handles exceptions if the image cannot be loaded
     *
     * @param  clazz         the class of the button
     * @param  path          the path to the button image
     * @param  buttonStyle   the style of the button
     * @param  buttonSize    the size of the button
     * @return               the created filter events button
     */
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

    /**
     * Creates a search events button with the specified class, path, button style, and button size.
     *
     * Handles exceptions if the image cannot be loaded
     *
     * @param  clazz        the class for the button
     * @param  path         the path for the button image
     * @param  buttonStyle  the style for the button
     * @param  buttonSize   the size for the button
     * @return              the created search events button
     */
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

    /**
     * Creates a Button object for viewing events.
     *
     * Handles exceptions if the image cannot be loaded
     *
     * @param  clazz         the class object associated with the button
     * @param  path          the path to the button image
     * @param  buttonStyle   the style of the button
     * @param  buttonSize    the size of the button
     * @return               the created Button object
     */
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

    /**
     * Creates an "Add Event" button with the specified style and size.
     *
     * Handles exceptions if the image cannot be loaded
     *
     * @param  clazz         the class of the button
     * @param  path          the path to the button image
     * @param  buttonStyle   the style of the button
     * @param  buttonSize    the size of the button
     * @return               the created "Add Event" button
     */
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

    /**
     * Creates a view profile button with the given class, path, button style, and button size.
     *
     * Handles exceptions if the image cannot be loaded
     *
     * @param  clazz        the class that the button belongs to
     * @param  path         the path of the button's image
     * @param  buttonStyle  the style of the button
     * @param  buttonSize   the size of the button
     * @return              the created view profile button
     */
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

    /**
     * Sets the image of a button using the provided class, path, and limits.
     *
     * @param  button   the button to set the image on
     * @param  clazz    the class to use as the resource base
     * @param  path     the path to the image file
     * @param  limits   the value to adjust the width and height of the image by
     * @throws Exception if there is an error while setting the image
     */
    private static void setImage(Button button, Class<?> clazz, String path, double limits) throws Exception {
        ImageView buttonImage = new ImageView(new Image(clazz.getResource(path).toExternalForm()));

        buttonImage.setFitWidth(button.getWidth() + limits);
        buttonImage.setFitHeight(button.getHeight() + limits);

        buttonImage.setOpacity(0.7);

        button.setGraphic(buttonImage);
        button.setText("");
    }

}

