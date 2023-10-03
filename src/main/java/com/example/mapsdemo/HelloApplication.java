
package com.example.mapsdemo;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jdesktop.swingx.JXMapKit;

import javax.swing.*;
import java.io.IOException;




public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Map Embed Example");

        // Create a JavaFX SwingNode to host the map component
        SwingNode swingNode = new SwingNode();
        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            JXMapKit mapKit = new JXMapKit();
            mapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);


            // Create a JavaFX SwingNode to host the map component
            swingNode.setContent(mapKit);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*

    JFrame frame = new JFrame();
    JXMapKit kit = new JXMapKit();
       kit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
               JPanel parentPanel = new JPanel();
               parentPanel.setLayout(new BorderLayout());
               parentPanel.add(kit, BorderLayout.CENTER);
               frame.setContentPane(parentPanel);
               frame.pack();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);*/
