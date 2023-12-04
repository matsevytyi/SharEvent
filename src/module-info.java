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
    exports DATA_ACCESS;
    exports DATA_ACCESS.loadevents_dataaccess;
    exports ENTITY;
    exports INTERFACE_ADAPTER;
    exports INTERFACE_ADAPTER.add_event;
    exports INTERFACE_ADAPTER.delete_event;
    exports INTERFACE_ADAPTER.loadmap_adapter;
    exports INTERFACE_ADAPTER.loadevents_adapter;
    exports INTERFACE_ADAPTER.login_adapter;
    exports INTERFACE_ADAPTER.register_for_event;
    exports INTERFACE_ADAPTER.view_event;
    exports INTERFACE_ADAPTER.view_profile;
    exports USE_CASE.add_event;
    exports USE_CASE.delete_event;
    exports USE_CASE.filter;
    exports USE_CASE.loadmap;
    exports USE_CASE.loadevents;
    exports USE_CASE.login;
    exports USE_CASE.register_for_event;
    exports USE_CASE.view_event;
    exports USE_CASE.view_profile;
    exports VIEW;
    exports VIEW_CREATOR;

    //opens com.example.mapsdemo to javafx.fxml;
    //exports com.example.mapsdemo;
}