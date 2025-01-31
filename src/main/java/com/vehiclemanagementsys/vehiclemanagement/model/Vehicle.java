package com.vehiclemanagementsys.vehiclemanagement.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    private final LongProperty id = new SimpleLongProperty();
    private final StringProperty make = new SimpleStringProperty();
    private final StringProperty model = new SimpleStringProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();
    private final StringProperty color = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> price = new SimpleObjectProperty<>();
    private final IntegerProperty mileage = new SimpleIntegerProperty();
    private final StringProperty vehicleCondition = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<LocalDateTime> acquisitionDateTime = new SimpleObjectProperty<>();
    private final LongProperty supplierID = new SimpleLongProperty();
    private final ObjectProperty<LocalDateTime> soldDateTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDateTime> createdAt = new SimpleObjectProperty<>();
    private static final Map<Long, Vehicle> vehicles = new HashMap<>();
    private static final ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();

    public Vehicle(Long id, String make, String model, Integer year, String color, BigDecimal price, Integer mileage,
                   String vehicleCondition, String status, LocalDateTime acquisitionDateTime, Long supplierID,
                   LocalDateTime soldDateTime, LocalDateTime createdAt) {
        this.id.set(id);
        setMake(make);
        setModel(model);
        setYear(year);
        setColor(color);
        setPrice(price);
        setMileage(mileage);
        setVehicleCondition(vehicleCondition);
        setStatus(status);
        setAcquisitionDateTime(acquisitionDateTime);
        setSupplierID(supplierID);
        setSoldDateTime(soldDateTime);
        setCreatedAt(createdAt);

        addVehicle(this);
    }

    // Property Getters
    public LongProperty idProperty() {
        return id;
    }

    public StringProperty makeProperty() {
        return make;
    }

    public StringProperty modelProperty() {
        return model;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public StringProperty colorProperty() {
        return color;
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public IntegerProperty mileageProperty() {
        return mileage;
    }

    public StringProperty vehicleConditionProperty() {
        return vehicleCondition;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<LocalDateTime> acquisitionDateTimeProperty() {
        return acquisitionDateTime;
    }

    public LongProperty supplierIDProperty() {
        return supplierID;
    }

    public ObjectProperty<LocalDateTime> soldDateTimeProperty() {
        return soldDateTime;
    }

    public ObjectProperty<LocalDateTime> createdAtProperty() {
        return createdAt;
    }

    // Getters
    public Long getID() {
        return id.get();
    }

    public String getMake() {
        return make.get();
    }

    public String getModel() {
        return model.get();
    }

    public Integer getYear() {
        return year.get();
    }

    public String getColor() {
        return color.get();
    }

    public BigDecimal getPrice() {
        return price.get();
    }

    public Integer getMileage() {
        return mileage.get();
    }

    public String getVehicleCondition() {
        return vehicleCondition.get();
    }

    public String getStatus() {
        return status.get();
    }

    public LocalDateTime getAcquisitionDateTime() {
        return acquisitionDateTime.get();
    }

    public Long getSupplierID() {
        return supplierID.get();
    }

    public LocalDateTime getSoldDateTime() {
        return soldDateTime.get();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt.get();
    }

    // Setters
    public void setMake(String make) {
        this.make.set(make);
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public void setYear(Integer year) {
        this.year.set(year);
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    public void setMileage(Integer mileage) {
        this.mileage.set(mileage);
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition.set(vehicleCondition);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setAcquisitionDateTime(LocalDateTime acquisitionDateTime) {
        this.acquisitionDateTime.set(acquisitionDateTime);
    }

    public void setSupplierID(Long supplierID) {
        this.supplierID.set(supplierID);
    }

    public void setSoldDateTime(LocalDateTime soldDateTime) {
        this.soldDateTime.set(soldDateTime);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt.set(createdAt);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + getID() +
                ", make='" + getMake() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", color='" + getColor() + '\'' +
                ", price=" + getPrice() +
                ", mileage=" + getMileage() +
                ", vehicle_condition='" + getVehicleCondition() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", acquisitionDateTime=" + getAcquisitionDateTime() +
                ", supplierID=" + getSupplierID() +
                ", soldDateTime=" + getSoldDateTime() +
                ", createdAt=" + getCreatedAt() +
                '}';
    }

    public static void addVehicle(Vehicle vehicle) {
        if(vehicle == null){
            System.out.println("Vehicle is null. Was not added to Map and List");
        }
        else{
            if(!containsVehicle(vehicle.getID())){
                vehicles.put(vehicle.getID(), vehicle);
                vehicleList.add(vehicle);
            }
            else{
                System.out.println("Sale is already present!");
            }
        }
    }

    public static void update(Vehicle vehicle, String make, String model, int year, String color,
                              BigDecimal price, Integer mileage, String vehicleCondition,
                              String status, LocalDateTime acquisitionDateTime, Long supplierID,
                              LocalDateTime soldDateTime) {
        if(vehicle != null){
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setYear(year);
            vehicle.setColor(color);
            vehicle.setPrice(price);
            vehicle.setMileage(mileage);
            vehicle.setVehicleCondition(vehicleCondition);
            vehicle.setStatus(status);
            vehicle.setAcquisitionDateTime(acquisitionDateTime);
            vehicle.setSupplierID(supplierID);
            vehicle.setSoldDateTime(soldDateTime);
        }
        else{
            System.out.println("Vehicle value is null");
        }
    }

    public static void removeVehicle(Vehicle vehicle) {
        if(validVehicle(vehicle)){
            vehicleList.remove(vehicle);
            vehicles.remove(vehicle.getID());
        }
        else{
            System.out.println("Vehicle ID not found");
        }
    }

    public static Vehicle getVehicle(Long vehicleId) {
        if(containsVehicle(vehicleId)){
            return vehicles.get(vehicleId);
        }
        else{
            System.out.println("Vehicle ID not found. Null was returned");
            return null;
        }
    }

    public static boolean validVehicle(Vehicle vehicle) {
        return vehicle != null && containsVehicle(vehicle.getID());
    }

    public static boolean containsVehicle(Long vehicleId) {
        return vehicles.containsKey(vehicleId);
    }

    public static ObservableList<Vehicle> getVehicleList(){
        return vehicleList;
    }

    public static void emptyVehicles() {
        vehicles.clear();
        vehicleList.clear();
    }
}
