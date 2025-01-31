package com.vehiclemanagementsys.vehiclemanagement.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Supplier {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private static final Map<Long, Supplier> suppliers = new HashMap<>();
    private static final ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    public Supplier(Long supplierID, String firstName, String lastName, String phoneNumber, String email, String location, LocalDateTime createdAt) {
        this.id.set(supplierID);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setLocation(location);
        setCreatedAt(createdAt);

        addSupplier(this);
    }

    // Properties
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    // Getters
    public Long getID() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getLocation() {
        return location.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + getID() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }

    public static void addSupplier(Supplier supplier) {
        if(supplier == null){
            System.out.println("Supplier is null. Was not added to Map and List");
        }
        else{
            if(!containsSupplier(supplier.getID())){
                suppliers.put(supplier.getID(), supplier);
                supplierList.add(supplier);
            }
            else{
                System.out.println("Supplier is already present!");
            }
        }
    }

    public static void update(Supplier supplier, String firstName, String lastName, String phoneNumber, String email, String location) {
        if(supplier != null){
            supplier.setFirstName(firstName);
            supplier.setLastName(lastName);
            supplier.setPhoneNumber(phoneNumber);
            supplier.setEmail(email);
            supplier.setLocation(location);
        }
        else{
            System.out.println("Supplier value is null");
        }
    }

    public static void removeSupplier(Supplier supplier) {
        if(supplier != null){
            supplierList.remove(supplier);
            suppliers.remove(supplier.getID());
        }
        else{
            System.out.println("Supplier ID not found");
        }
    }

    public static Supplier getSupplier(Long supplierId) {
        if(containsSupplier(supplierId)){
            return suppliers.get(supplierId);
        }
        else{
            System.out.println("Supplier ID not found. Null was returned");
            return null;
        }
    }

    public static boolean validSupplier(Supplier supplier) {
        return supplier != null && containsSupplier(supplier.getID());
    }

    public static boolean containsSupplier(Long supplierId) {
        return suppliers.containsKey(supplierId);
    }

    public static ObservableList<Supplier> getSupplierList(){
        return supplierList;
    }

    public static void emptySuppliers() {
        suppliers.clear();
        supplierList.clear();
    }
}
