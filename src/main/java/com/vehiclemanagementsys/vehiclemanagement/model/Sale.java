package com.vehiclemanagementsys.vehiclemanagement.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Sale {
    private final LongProperty id = new SimpleLongProperty();
    private final LongProperty vehicleID = new SimpleLongProperty();
    private final LongProperty customerID = new SimpleLongProperty();
    private final ObjectProperty<LocalDateTime> saleDateTime = new SimpleObjectProperty<>();
    private final ObjectProperty<BigDecimal> amountPaid = new SimpleObjectProperty<>();
    private final StringProperty paymentStatus = new SimpleStringProperty();
    private static final Map<Long, Sale> sales = new HashMap<>();
    private static final ObservableList<Sale> saleList = FXCollections.observableArrayList();

    public Sale(Long id, Long vehicleID, Long customerID, LocalDateTime saleDateTime, BigDecimal amountPaid, String paymentStatus) {
        this.id.set(id);
        setVehicleID(vehicleID);
        setCustomerID(customerID);
        setSaleDateTime(saleDateTime);
        setAmountPaid(amountPaid);
        setPaymentStatus(paymentStatus);

        addSale(this);
    }

    // Properties
    public LongProperty idProperty() {
        return id;
    }

    public LongProperty vehicleIDProperty() {
        return vehicleID;
    }

    public LongProperty customerIDProperty() {
        return customerID;
    }

    public ObjectProperty<LocalDateTime> saleDateTimeProperty() {
        return saleDateTime;
    }

    public ObjectProperty<BigDecimal> amountPaidProperty() {
        return amountPaid;
    }

    public StringProperty paymentStatusProperty() {
        return paymentStatus;
    }

    // Getters
    public Long getID() {
        return id.get();
    }

    public Long getVehicleID() {
        return vehicleID.get();
    }

    public Long getCustomerID() {
        return customerID.get();
    }

    public LocalDateTime getSaleDateTime() {
        return saleDateTime.get();
    }

    public BigDecimal getAmountPaid() {
        return amountPaid.get();
    }

    public String getPaymentStatus() {
        return paymentStatus.get();
    }

    // Setters
    public void setVehicleID(Long vehicleID) {
        this.vehicleID.set(vehicleID);
    }

    public void setCustomerID(Long customerID) {
        this.customerID.set(customerID);
    }

    public void setSaleDateTime(LocalDateTime saleDateTime) {
        this.saleDateTime.set(saleDateTime);
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid.set(amountPaid);
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus.set(paymentStatus);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + getID() +
                ", vehicleID=" + getVehicleID() +
                ", customerID=" + getCustomerID() +
                ", saleDateTime=" + getSaleDateTime() +
                ", amountPaid=" + getAmountPaid() +
                ", paymentStatus='" + getPaymentStatus() + '\'' +
                '}';
    }

    public static void addSale(Sale sale) {
        if(sale == null){
            System.out.println("Sale is null. Was not added to Map and List");
        }
        else{
            if(!containsSale(sale.getID())){
                sales.put(sale.getID(), sale);
                saleList.add(sale);
            }
            else{
                System.out.println("Sale is already present!");
            }
        }
    }

    public static void update(Sale sale, Long vehicleID, Long customerID, LocalDateTime saleDateTime, BigDecimal amountPaid, String paymentStatus) {
        if(sale != null){
            sale.setVehicleID(vehicleID);
            sale.setCustomerID(customerID);
            sale.setSaleDateTime(saleDateTime);
            sale.setAmountPaid(amountPaid);
            sale.setPaymentStatus(paymentStatus);
        }
        else{
            System.out.println("Sale value is null");
        }
    }

    public static void removeSale(Sale sale) {
        if(validSale(sale)){
            saleList.remove(sale);
            sales.remove(sale.getID());
        }
        else{
            System.out.println("Sale ID not found");
        }
    }

    public static Sale getSale(Long saleId) {
        if(containsSale(saleId)){
            return sales.get(saleId);
        }
        else{
            System.out.println("Sale ID not found. Null was returned");
            return null;
        }
    }

    public static boolean validSale(Sale sale) {
        return sale != null && containsSale(sale.getID());
    }

    public static boolean containsSale(Long saleId) {
        return sales.containsKey(saleId);
    }

    public static ObservableList<Sale> getSaleList(){
        return saleList;
    }

    public static void emptySales() {
        sales.clear();
        saleList.clear();
    }
}
