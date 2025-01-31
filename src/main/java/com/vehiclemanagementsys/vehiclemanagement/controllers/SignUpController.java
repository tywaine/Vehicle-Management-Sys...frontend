package com.vehiclemanagementsys.vehiclemanagement.controllers;

import com.vehiclemanagementsys.vehiclemanagement.enums.HttpStatus;
import com.vehiclemanagementsys.vehiclemanagement.model.Model;
import com.vehiclemanagementsys.vehiclemanagement.model.User;
import com.vehiclemanagementsys.vehiclemanagement.services.UserService;
import com.vehiclemanagementsys.vehiclemanagement.utils.MyAlert;
import com.vehiclemanagementsys.vehiclemanagement.utils.MyBCrypt;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    public Button btnSignUp;
    public TextField txtUsername, txtPassword, txtConfirmPassword;
    public PasswordField pwdPassword, pwdConfirmPassword;
    public Label lblUsernameError;
    public CheckBox chkPasswordVisible;

    private final UserService userService = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtConfirmPassword.setVisible(false);
        txtPassword.setVisible(false);
        btnSignUp.setDisable(true);

        txtUsername.setOnKeyReleased(this::handleUsernameKeyReleased);

        txtUsername.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        txtConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        pwdPassword.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
        pwdConfirmPassword.textProperty().addListener((observable, oldValue, newValue) -> validateFields());
    }

    private void handleUsernameKeyReleased(KeyEvent keyEvent) {
        String name = txtUsername.getText().trim();

        if(User.isValidUsername(name)){
            if(name.length() > 50){
                lblUsernameError.setText("Name Should not be longer that 50 characters");
            }
            else{
                lblUsernameError.setText("");
            }
        }
        else{
            lblUsernameError.setText("Invalid Name");
        }

        validateFields();
    }

    private void validateFields() {
        String username = txtUsername.getText().trim();
        String password = (txtPassword.isVisible()) ? txtPassword.getText() : pwdPassword.getText();
        String confirmPassword = (txtConfirmPassword.isVisible()) ? txtConfirmPassword.getText() : pwdConfirmPassword.getText();

        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            btnSignUp.setDisable(true);
            return;
        }

        if(!lblUsernameError.getText().isEmpty()){
            btnSignUp.setDisable(true);
            return;
        }

        btnSignUp.setDisable(false);
    }

    public void signUp() {
        String username = txtUsername.getText().trim();
        String password = (txtPassword.isVisible()) ? txtPassword.getText() : pwdPassword.getText();
        String confirmPassword = (txtConfirmPassword.isVisible()) ? txtConfirmPassword.getText() : pwdConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            MyAlert.showAlert(Alert.AlertType.ERROR, "Password doesn't match", "Password and Confirm Password do not match...");
            return;
        }

        if (password.length() < 8) {
            MyAlert.showAlert(Alert.AlertType.ERROR, "Password too short", "Password must be at least 8 characters long");
            return;
        }

        try {
            // Create a new user object
            User newAdmin = new User(username, MyBCrypt.hashPassword(password), "Admin", LocalDateTime.now());

            // Send the request and get the response
            HttpResponse<String> response = userService.sendCreateUserRequest(newAdmin);

            if (response.statusCode() == HttpStatus.CREATED.getCode()) { // User created successfully
                MyAlert.showAlert(Alert.AlertType.INFORMATION, "Successfully created User", "User was created successfully");
                Stage stage = (Stage) lblUsernameError.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showLoginWindow();
                MyAlert.showAlert(Alert.AlertType.INFORMATION, "Login Information", "Your Login information is:\n" +
                        "Username: " + username + "\nPassword: <password you entered>");
            }
            else {
                MyAlert.showAlert(Alert.AlertType.ERROR, "User already exists", response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while creating the user");
        }
    }

    public void passwordVisible(ActionEvent actionEvent) {
        if(chkPasswordVisible.isSelected()){
            txtPassword.setText(pwdPassword.getText());
            txtConfirmPassword.setText(pwdConfirmPassword.getText());
            pwdPassword.setVisible(false);
            pwdConfirmPassword.setVisible(false);
            txtPassword.setVisible(true);
            txtConfirmPassword.setVisible(true);
        }
        else{
            pwdPassword.setText(txtPassword.getText());
            pwdConfirmPassword.setText(txtConfirmPassword.getText());
            pwdPassword.setVisible(true);
            pwdConfirmPassword.setVisible(true);
            txtPassword.setVisible(false);
            txtConfirmPassword.setVisible(false);
        }
    }
}
