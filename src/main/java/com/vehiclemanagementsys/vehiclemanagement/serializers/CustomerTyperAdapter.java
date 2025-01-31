package com.vehiclemanagementsys.vehiclemanagement.serializers;

import com.google.gson.*;
import com.vehiclemanagementsys.vehiclemanagement.model.Customer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class CustomerTyperAdapter implements JsonSerializer<Customer>, JsonDeserializer<Customer> {
    @Override
    public JsonElement serialize(Customer customer, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", customer.getFirstName());
        jsonObject.addProperty("lastName", customer.getLastName());
        jsonObject.addProperty("phoneNumber", customer.getPhoneNumber());
        jsonObject.addProperty("email", customer.getEmail());
        jsonObject.addProperty("address", customer.getAddress());
        jsonObject.addProperty("createdAt", customer.getCreatedAt().toString());
        return jsonObject;
    }

    @Override
    public Customer deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext){
        try{
            JsonObject jsonObject = json.getAsJsonObject();
            Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null;
            String firstName = jsonObject.get("firstName").getAsString();
            String lastName = jsonObject.get("lastName").getAsString();
            String phoneNumber = jsonObject.get("phoneNumber").getAsString();
            String email = jsonObject.get("email").getAsString();
            String address = jsonObject.get("address").getAsString();
            LocalDateTime createdAt = LocalDateTime.parse(jsonObject.get("createdAt").getAsString());

            return new Customer(id, firstName, lastName, phoneNumber, email, address, createdAt);
        }catch(JsonParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
