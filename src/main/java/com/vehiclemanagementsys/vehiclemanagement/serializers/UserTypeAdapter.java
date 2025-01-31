package com.vehiclemanagementsys.vehiclemanagement.serializers;

import com.google.gson.*;
import com.vehiclemanagementsys.vehiclemanagement.model.User;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class UserTypeAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

    @Override
    public JsonElement serialize(User user, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", user.getUsername());
        jsonObject.addProperty("passwordHash", user.getPasswordHash());
        jsonObject.addProperty("role", user.getRole());
        jsonObject.addProperty("createdAt", user.getCreatedAt().toString());
        return jsonObject;
    }

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        try{
            JsonObject jsonObject = json.getAsJsonObject();
            Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null;
            String username = jsonObject.get("username").getAsString();
            String passwordHash = jsonObject.get("passwordHash").getAsString();
            String role = jsonObject.get("role").getAsString();
            LocalDateTime createdAt = LocalDateTime.parse(jsonObject.get("createdAt").getAsString());

            return new User(id, username, passwordHash, role, createdAt);
        }catch (JsonParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
