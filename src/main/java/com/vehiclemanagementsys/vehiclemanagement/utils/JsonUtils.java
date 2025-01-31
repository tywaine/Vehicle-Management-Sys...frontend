package com.vehiclemanagementsys.vehiclemanagement.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {
    // Base Gson instance without any specific adapters
    private static final Gson defaultGson = new GsonBuilder().create();

    // Method to get the default Gson instance
    public static Gson getDefaultGson() {
        return defaultGson;
    }

    // Method to create a Gson instance with a specific type adapter
    public static <T> Gson getGsonWithAdapter(Class<T> type, Object serializer) {
        return new GsonBuilder()
                .registerTypeAdapter(type, serializer)
                .create();
    }

    // Method to convert an object to JSON with a specific adapter
    public static <T> String toJsonWithAdapter(T object, Class<T> type, Object serializer) {
        return getGsonWithAdapter(type, serializer).toJson(object);
    }

    // General-purpose serialization without a specific adapter
    public static String toJson(Object object) {
        return defaultGson.toJson(object);
    }

    // Method to parse JSON to an object
    public static <T> T fromJson(String json, Class<T> clazz) {
        return defaultGson.fromJson(json, clazz);
    }

    public static <T> T fromJsonWithAdapter(String json, Class<T> clazz, Object deserializer) {
        return getGsonWithAdapter(clazz, deserializer).fromJson(json, clazz);
    }
}
