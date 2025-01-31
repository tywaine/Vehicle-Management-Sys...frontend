package com.vehiclemanagementsys.vehiclemanagement.controllers.Admin;

import com.vehiclemanagementsys.vehiclemanagement.controllers.LoginController;
import com.vehiclemanagementsys.vehiclemanagement.enums.AdminMenuOptions;
import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button btnVehicles, btnSales, btnManageStaff, btnSuppliers,
            btnCustomers, btnAccount, btnSignOut;
    public Label lblRole;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblRole.setText("ID Number: " + Model.getInstance().getCurrentUser().getID());
        setIcons();
        addListeners();
        btnVehicles.getStyleClass().add("button-selected");
    }

    private void setIcons() {
        btnVehicles.setGraphic(createFontIcon(FontAwesomeSolid.CAR, 18)); // Vehicle Management
        btnSales.setGraphic(createFontIcon(FontAwesomeSolid.DOLLAR_SIGN, 18)); // Sales
        btnManageStaff.setGraphic(createFontIcon(FontAwesomeSolid.USERS_COG, 18)); // Manage Staff
        btnSuppliers.setGraphic(createFontIcon(FontAwesomeSolid.TRUCK_LOADING, 18)); // Suppliers
        btnCustomers.setGraphic(createFontIcon(FontAwesomeSolid.USER_FRIENDS, 18)); // Manage Customers
        btnAccount.setGraphic(createFontIcon(FontAwesomeSolid.USER, 18)); // Account
        btnSignOut.setGraphic(createFontIcon(FontAwesomeSolid.SIGN_OUT_ALT, 14)); // Sign Out
    }

    private FontIcon createFontIcon(FontAwesomeSolid iconType, int size) {
        FontIcon icon = new FontIcon(iconType);
        icon.setIconSize(size);
        icon.getStyleClass().add("icon");
        return icon;
    }

    private void addListeners() {
        addButtonListener(btnVehicles, AdminMenuOptions.VEHICLES);
        addButtonListener(btnSales, AdminMenuOptions.SALES);
        addButtonListener(btnManageStaff, AdminMenuOptions.MANAGE_STAFF);
        addButtonListener(btnSuppliers, AdminMenuOptions.SUPPLIERS);
        addButtonListener(btnCustomers, AdminMenuOptions.CUSTOMERS);
        addButtonListener(btnAccount, AdminMenuOptions.ACCOUNT);
        btnSignOut.setOnAction(event -> onSignOut());
    }

    private void addButtonListener(Button button, AdminMenuOptions menuOption) {
        button.setOnAction(event -> {
            clearButtonStyles();
            button.getStyleClass().add("button-selected");
            Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(menuOption);
        });
    }

    private void clearButtonStyles() {
        btnVehicles.getStyleClass().remove("button-selected");
        btnSales.getStyleClass().remove("button-selected");
        btnManageStaff.getStyleClass().remove("button-selected");
        btnSuppliers.getStyleClass().remove("button-selected");
        btnCustomers.getStyleClass().remove("button-selected");
        btnAccount.getStyleClass().remove("button-selected");
    }

    private void onSignOut(){
        Stage stage = (Stage) btnVehicles.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().resetViewFactory();
        LoginController.removeCredentials();
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
