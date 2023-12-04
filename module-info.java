module com.example.mapsdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires jxmapviewer2;
    requires lombok;
    requires java.sql;
    requires com.google.gson;
    exports APP;
    exports APP.use_case_factory;
    exports INTERFACE_ADAPTER.add_event;

    //opens com.example.mapsdemo to javafx.fxml;
    //exports com.example.mapsdemo;
}