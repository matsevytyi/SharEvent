module com.example.mapsdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires jxmapviewer2;
    requires lombok;
    requires java.sql;
    requires com.google.gson;

    exports APP;
    exports USE_CASE.loadmap;
    exports INTERFACE_ADAPTER.loadmap_adapter;
    exports VIEW;
    exports VIEW_CREATOR;
    exports USE_CASE.login;
    exports USE_CASE.loadevents;
    exports INTERFACE_ADAPTER.loadevents_adapter;
    exports ENTITY;
    //opens com.example.mapsdemo to javafx.fxml;
    //exports com.example.mapsdemo;
}