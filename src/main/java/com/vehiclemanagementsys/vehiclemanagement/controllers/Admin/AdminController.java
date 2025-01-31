package com.vehiclemanagementsys.vehiclemanagement.controllers.Admin;

import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane adminParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener(
                (observableValue, oldVal, newVal) -> {
                    switch(newVal){

                    }
                }
        );
    }
}
