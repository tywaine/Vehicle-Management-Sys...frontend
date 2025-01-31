package com.vehiclemanagementsys.vehiclemanagement.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty passwordHash = new SimpleStringProperty();
    private final StringProperty role = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private final StringProperty createdAtFormatted = new SimpleStringProperty();
    private static final Map<Long, User> staffs = new HashMap<>();
    private static final ObservableList<User> staffList = FXCollections.observableArrayList();

    public User(String username, String passwordHash, String role, LocalDateTime createdAt) {
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setCreatedAt(createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createdAtFormatted.set(createdAt.format(formatter));

        addStaff(this);
    }

    public User(Long id, String username, String passwordHash, String role, LocalDateTime createdAt) {
        this.id.set(id);
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setCreatedAt(createdAt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.createdAtFormatted.set(createdAt.format(formatter));

        addStaff(this);
    }

    // Properties
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordHashProperty() {
        return passwordHash;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    // Getters
    public Long getID() {
        return id.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPasswordHash() {
        return passwordHash.get();
    }

    public String getRole() {
        return role.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    public String getCreatedAtFormatted() {
        return createdAtFormatted.get();
    }

    public StringProperty createdAtFormattedProperty(){
        return createdAtFormatted;
    }

    // Setters
    public void setID(Long id) {
        this.id.set(id);
    }
    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash.set(passwordHash);
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getID() +
                ", username='" + getUsername() + '\'' +
                ", passwordHash='" + getPasswordHash() + '\'' +
                ", role='" + getRole() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }

    public boolean isStaff(){
        return getRole().equals("Staff");
    }

    public boolean isAdmin(){
        return getRole().equals("Admin");
    }

    public static boolean isValidUsername(String name) {
        // Trim any leading or trailing whitespace
        String trimmedName = name.trim();

        // Check if the string contains no spaces and matches the pattern
        return trimmedName.matches("^(?=.{1,}$)[A-Za-z0-9]+(?:_[A-Za-z0-9]+)*$");
    }

    public static boolean isValidPasswordLength(String password){
        return password.length() >= 8 && password.length() <= 50;
    }

    public static void addStaff(User user) {
        if(user == null){
            System.out.println("User is null. Was not added to Map and List");
            return;
        }
        if(user.isStaff()){
            if(!containsStaff(user.getID())){
                staffs.put(user.getID(), user);
                staffList.add(user);
            }
            else{
                System.out.println("Staff is already present!");
            }
        }
        else{
            System.out.println("User's role is not STAFF");
        }
    }

    public static void update(User user, String username, String passwordHash, String role){
        if(user != null){
            user.setUsername(username);
            user.setPasswordHash(passwordHash);
            user.setRole(role);
        }
        else{
            System.out.println("User value is null");
        }
    }

    public static void removeStaff(User staff) {
        if(validStaff(staff)){
            staffList.remove(staff);
            staffs.remove(staff.getID());
        }
        else{
            System.out.println("Staff ID not found");
        }
    }

    public static User getStaff(Long staffId) {
        if(containsStaff(staffId)){
            return staffs.get(staffId);
        }
        else{
            System.out.println("Staff ID not found. Null was returned");
            return null;
        }
    }

    public static boolean validStaff(User staff){
        return staff != null && containsStaff(staff.getID());
    }

    public static boolean containsStaff(Long staffID) {
        return staffs.containsKey(staffID);
    }

    public static ObservableList<User> getStaffList(){
        return staffList;
    }

    public static void emptyStaff() {
        staffs.clear();
        staffList.clear();
    }
}
