package com.vehiclemanagementsys.vehiclemanagement.view;

import com.vehiclemanagementsys.vehiclemanagement.controllers.Admin.AdminController;
import com.vehiclemanagementsys.vehiclemanagement.controllers.LoginController;
import com.vehiclemanagementsys.vehiclemanagement.controllers.Staff.StaffController;
import com.vehiclemanagementsys.vehiclemanagement.enums.AdminMenuOptions;
import com.vehiclemanagementsys.vehiclemanagement.enums.StaffMenuOptions;
import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import com.vehiclemanagementsys.vehiclemanagement.services.UserService;
import com.vehiclemanagementsys.vehiclemanagement.utils.MyAlert;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    //Admin Views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;

    //Staff View
    private final ObjectProperty<StaffMenuOptions> staffSelectedMenuItem;

    //Shared Views
    private AnchorPane dashboardView;

    public ViewFactory(){
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
        this.staffSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public ObjectProperty<StaffMenuOptions> getStaffSelectedMenuItem(){
        return staffSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null){
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/dashboard.fxml")).load();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return dashboardView;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void showStaffWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Staff/staff.fxml"));
        StaffController staffController = new StaffController();
        loader.setController(staffController);
        createStage(loader);
    }

    public void showSignUpWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/signUp.fxml"));
        createStage(loader, "Sign Up");
        MyAlert.showAlert(Alert.AlertType.INFORMATION, "First time creating a User account",
                """
                        Since there is no user registered in the system you will create an Account.
                        Make sure to remember the details, specifically the EMAIL and obviously the PASSWORD.
                        You will need them to login. THERE IS NO RECOVERY SYSTEM (As of now)
                        You can change the password later if you like""");
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        createStage(loader);
    }

    public void decideWhatToShow(){
        if(UserService.doesAdminExist()){
            //DataBaseManager.loadInfo();
            Model.getInstance().getViewFactory().loginWindow();
        }
        else{
            LoginController.removeCredentials();
            showSignUpWindow();
        }
    }

    public void loginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/login.fxml"));
        LoginController loginController;

        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Vehicle Management");
            stage.setResizable(false);

            loginController = loader.getController();
            loginController.shouldShow();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStage(FXMLLoader loader){
        try{
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Vehicle Management");
            stage.show();
            stage.setResizable(false);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createStage(FXMLLoader loader, String title){
        try{
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();
            stage.setResizable(false);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
