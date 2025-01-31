package com.vehiclemanagementsys.vehiclemanagement.serializers;

import com.google.gson.*;
import com.vehiclemanagementsys.vehiclemanagement.model.Supplier;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class SupplierTypeAdapter implements JsonSerializer<Supplier>, JsonDeserializer<Supplier> {
    @Override
    public JsonElement serialize(Supplier supplier, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", supplier.getFirstName());
        jsonObject.addProperty("lastName", supplier.getLastName());
        jsonObject.addProperty("phoneNumber", supplier.getPhoneNumber());
        jsonObject.addProperty("email", supplier.getEmail());
        jsonObject.addProperty("location", supplier.getLocation());
        jsonObject.addProperty("createdAt", supplier.getCreatedAt().toString());
        return jsonObject;
    }

    @Override
    public Supplier deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext){
        try{
            JsonObject jsonObject = json.getAsJsonObject();
            Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null;
            String firstName = jsonObject.get("firstName").getAsString();
            String lastName = jsonObject.get("lastName").getAsString();
            String phoneNumber = jsonObject.get("phoneNumber").getAsString();
            String email = jsonObject.get("email").getAsString();
            String location = jsonObject.get("location").getAsString();
            LocalDateTime createdAt = LocalDateTime.parse(jsonObject.get("createdAt").getAsString());

            return new Supplier(id, firstName, lastName, phoneNumber, email, location,createdAt);
        }catch(JsonParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
