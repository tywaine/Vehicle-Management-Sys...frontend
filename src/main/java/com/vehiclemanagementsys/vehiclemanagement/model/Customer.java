package com.vehiclemanagementsys.vehiclemanagement.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty phoneNumber = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private static final Map<Long, Customer> customers = new HashMap<>();
    private static final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public Customer(Long id, String firstName, String lastName, String phoneNumber, String email, String address, LocalDateTime createdAt) {
        this.id.set(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setAddress(address);
        setCreatedAt(createdAt);

        addCustomer(this);
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

    public StringProperty addressProperty() {
        return address;
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

    public String getAddress() {
        return address.get();
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

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getID() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", createdAt=" + getCreatedAt() +
                '}';
    }

    public static void addCustomer(Customer customer) {
        if(customer == null){
            System.out.println("Customer is null. Was not added to Map and List");
        }
        else{
            if(!containsCustomer(customer.getID())){
                customers.put(customer.getID(), customer);
                customerList.add(customer);
            }
            else{
                System.out.println("Customer is already present!");
            }
        }
    }

    public static void update(Customer customer, String firstName, String lastName, String phoneNumber, String email, String address) {
        if(customer != null){
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhoneNumber(phoneNumber);
            customer.setEmail(email);
            customer.setAddress(address);
        }
        else{
            System.out.println("Customer value is null");
        }
    }

    public static void removeCustomer(Customer customer) {
        if(validCustomer(customer)){
            customerList.remove(customer);
            customers.remove(customer.getID());
        }
        else{
            System.out.println("Customer ID not found");
        }
    }

    public static Customer getCustomer(Long customerId) {
        if(containsCustomer(customerId)){
            return customers.get(customerId);
        }
        else{
            System.out.println("Customer ID not found. Null was returned");
            return null;
        }
    }

    public static boolean validCustomer(Customer customer) {
        return customer != null && containsCustomer(customer.getID());
    }

    public static boolean containsCustomer(Long customerId) {
        return customers.containsKey(customerId);
    }

    public static ObservableList<Customer> getCustomerList(){
        return customerList;
    }

    public static void emptyCustomers() {
        customers.clear();
        customerList.clear();
    }
}
