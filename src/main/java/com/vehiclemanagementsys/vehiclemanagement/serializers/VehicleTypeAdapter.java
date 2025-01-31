package com.vehiclemanagementsys.vehiclemanagement.serializers;

import com.google.gson.*;
import com.vehiclemanagementsys.vehiclemanagement.model.Vehicle;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehicleTypeAdapter implements JsonSerializer<Vehicle>, JsonDeserializer<Vehicle> {
    @Override
    public JsonElement serialize(Vehicle vehicle, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("make", vehicle.getMake());
        jsonObject.addProperty("model", vehicle.getModel());
        jsonObject.addProperty("year", vehicle.getYear());
        jsonObject.addProperty("color", vehicle.getColor());
        jsonObject.addProperty("price", vehicle.getPrice());
        jsonObject.addProperty("mileage", vehicle.getMileage());
        jsonObject.addProperty("vehicle_condition", vehicle.getVehicleCondition());
        jsonObject.addProperty("status", vehicle.getStatus());
        jsonObject.addProperty("acquisitionDateTime", vehicle.getAcquisitionDateTime().toString());
        jsonObject.addProperty("supplierID", vehicle.getSupplierID());
        jsonObject.addProperty("soldDateTime", vehicle.getSoldDateTime().toString());
        jsonObject.addProperty("createdAt", vehicle.getCreatedAt().toString());
        return jsonObject;
    }

    @Override
    public Vehicle deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try{
            JsonObject jsonObject = json.getAsJsonObject();
            Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null;
            String make = jsonObject.get("make").getAsString();
            String model = jsonObject.get("model").getAsString();
            Integer year = jsonObject.get("year").getAsInt();
            String color = jsonObject.get("color").getAsString();
            BigDecimal price = jsonObject.get("price").getAsBigDecimal();
            Integer mileage = jsonObject.get("mileage").getAsInt();
            String vehicleCondition = jsonObject.get("vehicle_condition").getAsString();
            String status = jsonObject.get("status").getAsString();
            LocalDateTime acquisitionDateTime = LocalDateTime.parse(jsonObject.get("acquisitionDateTime").getAsString());
            Long supplierID = jsonObject.get("supplierID").getAsLong();
            LocalDateTime soldDateTime = LocalDateTime.parse(jsonObject.get("soldDateTime").getAsString());
            LocalDateTime createdAt = LocalDateTime.parse(jsonObject.get("createdAt").getAsString());

            return new Vehicle(id, make, model, year, color, price, mileage, vehicleCondition, status, acquisitionDateTime, supplierID, soldDateTime, createdAt);
        }catch(JsonParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
