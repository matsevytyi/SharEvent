module com.example.mapsdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires jxmapviewer2;
    requires mapbox.sdk.geojson;


    opens com.example.mapsdemo to javafx.fxml;
    exports com.example.mapsdemo;
}