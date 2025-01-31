package com.vehiclemanagementsys.vehiclemanagement.controllers;

import com.vehiclemanagementsys.vehiclemanagement.enums.HttpStatus;
import com.vehiclemanagementsys.vehiclemanagement.enums.PreferenceKeys;
import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import com.vehiclemanagementsys.vehiclemanagement.model.User;
import com.vehiclemanagementsys.vehiclemanagement.services.UserService;
import com.vehiclemanagementsys.vehiclemanagement.utils.MyAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {
    public Button btnLogin, btnVisible;
    public TextField txtUsername, txtVisiblePassword;
    public CheckBox chkSaveCredentials;
    public Label lblError;
    public PasswordField pwdPassword;

    private final FontIcon visibleIcon = createFontIcon(FontAwesomeSolid.EYE);
    private final FontIcon invisibleIcon = createFontIcon(FontAwesomeSolid.EYE_SLASH);
    private static final Preferences preferences = Preferences.userNodeForPackage(LoginController.class);
    UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtVisiblePassword.setManaged(false);
        txtVisiblePassword.setVisible(false);
        txtVisiblePassword.textProperty().bindBidirectional(pwdPassword.textProperty());
        btnVisible.setOnAction(event -> togglePasswordVisibility());
    }

    private FontIcon createFontIcon(FontAwesomeSolid iconType) {
        FontIcon icon = new FontIcon(iconType);
        icon.getStyleClass().add("icon");
        return icon;
    }

    public void login(){
        String username = txtUsername.getText();
        String password = (pwdPassword.isVisible()) ? pwdPassword.getText() : txtVisiblePassword.getText();

        if(username.isEmpty() || password.isEmpty()){
            lblError.setText("Text field/s cannot be empty");
        }

        HttpResponse<String> response = userService.sendLoginRequest(username, password);

        if(response.statusCode() == HttpStatus.OK.getCode()){
            User user = userService.fetchUserByUsername(username);
            Model.getInstance().setCurrentUser(user);
            System.out.println(user);
            lblError.setText("");
            Stage stage = (Stage) lblError.getScene().getWindow();
            showCorrectWindow(username, password, stage, user.getRole());
        }
        else{
            lblError.setText(response.body());
        }
    }

    private void togglePasswordVisibility() {
        if (txtVisiblePassword.isVisible()) {
            btnVisible.setGraphic(visibleIcon);

            txtVisiblePassword.setManaged(false);
            txtVisiblePassword.setVisible(false);
            pwdPassword.setManaged(true);
            pwdPassword.setVisible(true);
        }
        else {
            btnVisible.setGraphic(invisibleIcon);

            txtVisiblePassword.setManaged(true);
            txtVisiblePassword.setVisible(true);
            pwdPassword.setManaged(false);
            pwdPassword.setVisible(false);
        }
    }

    public static void removeCredentials(){
        preferences.put(PreferenceKeys.USER_USERNAME.getKey(), "");
        preferences.put(PreferenceKeys.USER_PASSWORD.getKey(), "");
    }

    public void shouldShow() {
        String username = preferences.get(PreferenceKeys.USER_USERNAME.getKey(), "");
        String password = preferences.get(PreferenceKeys.USER_PASSWORD.getKey(), "");
        Stage stage = (Stage) lblError.getScene().getWindow();

        HttpResponse<String> response = userService.sendLoginRequest(username, password);

        if(response.statusCode() == HttpStatus.OK.getCode()){
            User user = userService.fetchUserByUsername(username);
            Model.getInstance().setCurrentUser(user);
            System.out.println(user);
            lblError.setText("");
            showCorrectWindow(username, password, stage, user.getRole());
        }
        else{
            removeCredentials();
            stage.show();
        }
    }

    private void showCorrectWindow(String email, String password, Stage stage, String role) {
        Model.getInstance().getViewFactory().closeStage(stage);

        if(role.equals("Admin")){
            if(chkSaveCredentials.isSelected()){
                preferences.put(PreferenceKeys.USER_USERNAME.getKey(), email);
                preferences.put(PreferenceKeys.USER_PASSWORD.getKey(), password);
            }

            Model.getInstance().getViewFactory().showAdminWindow();
        }
        else{
            if(role.equals("Staff")){
                if(chkSaveCredentials.isSelected()){
                    preferences.put(PreferenceKeys.USER_USERNAME.getKey(), email);
                    preferences.put(PreferenceKeys.USER_PASSWORD.getKey(), password);
                }

                Model.getInstance().getViewFactory().showStaffWindow();
            }
            else{
                MyAlert.showAlert(Alert.AlertType.ERROR, "There is no User other than Admin and Staff", "How did this happen?????");
                System.exit(1);
            }
        }
    }
}
