module com.vehiclemanagementsys.vehiclemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires jbcrypt;
    requires java.prefs;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires jakarta.mail;
    requires jakarta.activation;
    requires com.github.librepdf.openpdf;
    requires com.google.gson;

    opens com.vehiclemanagementsys.vehiclemanagement to javafx.fxml;
    exports com.vehiclemanagementsys.vehiclemanagement;
    exports com.vehiclemanagementsys.vehiclemanagement.services;
    exports com.vehiclemanagementsys.vehiclemanagement.controllers;
    exports com.vehiclemanagementsys.vehiclemanagement.controllers.Admin;
    exports com.vehiclemanagementsys.vehiclemanagement.controllers.Staff;
    exports com.vehiclemanagementsys.vehiclemanagement.enums;
    exports com.vehiclemanagementsys.vehiclemanagement.model;
    exports com.vehiclemanagementsys.vehiclemanagement.utils;
    exports com.vehiclemanagementsys.vehiclemanagement.view;
}