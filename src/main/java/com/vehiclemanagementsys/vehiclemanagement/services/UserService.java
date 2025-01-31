package com.vehiclemanagementsys.vehiclemanagement.services;

import com.vehiclemanagementsys.vehiclemanagement.enums.HttpStatus;
import com.vehiclemanagementsys.vehiclemanagement.enums.MyUrl;
import com.vehiclemanagementsys.vehiclemanagement.model.User;
import com.vehiclemanagementsys.vehiclemanagement.serializers.UserTypeAdapter;
import com.vehiclemanagementsys.vehiclemanagement.utils.HttpRequestUtil;
import com.vehiclemanagementsys.vehiclemanagement.utils.JsonUtils;
import com.vehiclemanagementsys.vehiclemanagement.utils.MyAlert;
import javafx.scene.control.Alert;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class UserService {
    private static final String baseUrl = MyUrl.API_URL.getUrl() + "Users";

    public HttpResponse<String> sendCreateUserRequest(User user) {
        try {
            // Convert user object to JSON with the UserSerializer
            String userJson = JsonUtils.toJsonWithAdapter(user, User.class, new UserTypeAdapter());
            return HttpRequestUtil.sendRequest("POST", baseUrl, Map.of("Content-Type", "application/json"), userJson);
        } catch (Exception e) {
            e.printStackTrace();
            MyAlert.showAlert(Alert.AlertType.ERROR, "Error",
                    "An error occurred while sending the create user post request: " + e.getMessage());
            return null; // Return null in case of an error
        }
    }

    public HttpResponse<String> sendLoginRequest(String username, String password) {
        try {
            String loginUrl = baseUrl + "/login";

            // Create a query string
            String requestBody = String.format("username=%s&password=%s", username, password);
            return HttpRequestUtil.sendRequest("POST", loginUrl, Map.of("Content-Type", "application/x-www-form-urlencoded"), requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void fetchUsers(Long id) {
        try {
            // Build the URL based on the presence of ID
            String url = (id != null) ? baseUrl + "?id=" + id : baseUrl;

            // Send the request
            HttpResponse<String> response = HttpRequestUtil.sendRequest("GET", url, null);

            // Check the response status code
            if (response.statusCode() == HttpStatus.OK.getCode()) {
                // Convert JSON response to List<User>
                System.out.println(response.body());
                JsonUtils.fromJsonWithAdapter(response.body(), User[].class, new UserTypeAdapter());
                System.out.println(User.getStaffList());
            } else {
                MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch users: " + response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while fetching users: " + e.getMessage());
        }
    }

    /**
     * Fetches a user by their username.
     *
     * @param username The username of the user to fetch.
     * @return The User object or null if not found or in case of an error.
     */
    public User fetchUserByUsername(String username) {
        try {
            // Build the URL
            String usernameUrl = baseUrl + "/username/" + username;

            // Send the request
            HttpResponse<String> response = HttpRequestUtil.sendRequest("GET", usernameUrl, null);

            // Check the response status code
            if (response.statusCode() == HttpStatus.OK.getCode()) {
                // Convert JSON response to User
                System.out.println(response.body());
                return JsonUtils.fromJsonWithAdapter(response.body(), User.class, new UserTypeAdapter());
            }
            else if (response.statusCode() == HttpStatus.NOT_FOUND.getCode()) {
                MyAlert.showAlert(Alert.AlertType.INFORMATION, "Not Found", "User not found.");
                return null;
            }
            else {
                MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch user: " + response.body());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while fetching the user: " + e.getMessage());
            return null;
        }
    }

    public static boolean doesAdminExist() {
        try {
            // Build the URL
            String doesAdminExistUrl = baseUrl + "/doesAdminExists";

            // Send the request
            HttpResponse<String> response = HttpRequestUtil.sendRequest("POST", doesAdminExistUrl, null, null);

            // Check the response status code
            if (response.statusCode() == HttpStatus.FOUND.getCode()) {
                // Convert JSON response to User
                System.out.println(response.body());
                return true;
            }
            else {
                System.out.println(response.body());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyAlert.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while fetching the user: " + e.getMessage());
            return false;
        }
    }
}
