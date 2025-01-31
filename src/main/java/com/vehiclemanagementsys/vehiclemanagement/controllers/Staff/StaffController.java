package com.vehiclemanagementsys.vehiclemanagement.controllers.Staff;

import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController implements Initializable {
    public BorderPane staffParent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getStaffSelectedMenuItem().addListener(
                (observableValue, oldVal, newVal) -> {
                    switch(newVal){

                    }
                }
        );
    }
}
