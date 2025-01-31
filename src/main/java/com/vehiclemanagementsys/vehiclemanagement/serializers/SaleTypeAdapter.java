package com.vehiclemanagementsys.vehiclemanagement.serializers;

import com.google.gson.*;
import com.vehiclemanagementsys.vehiclemanagement.model.Sale;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleTypeAdapter implements JsonSerializer<Sale>, JsonDeserializer<Sale> {

    @Override
    public JsonElement serialize(Sale sale, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("vehicleID", sale.getVehicleID());
        jsonObject.addProperty("customerID", sale.getCustomerID());
        jsonObject.addProperty("saleDateTime", sale.getSaleDateTime().toString());
        jsonObject.addProperty("amountPaid", sale.getAmountPaid());
        jsonObject.addProperty("paymentStatus", sale.getPaymentStatus());
        return jsonObject;
    }

    @Override
    public Sale deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context){
        try{
            JsonObject jsonObject = json.getAsJsonObject();
            Long id = jsonObject.has("id") ? jsonObject.get("id").getAsLong() : null;
            Long vehicleID = jsonObject.get("vehicleID").getAsLong();
            Long customerID = jsonObject.get("customerID").getAsLong();
            LocalDateTime saleDateTime = LocalDateTime.parse(jsonObject.get("saleDateTime").getAsString());
            BigDecimal amountPaid = jsonObject.get("amountPaid").getAsBigDecimal();
            String paymentStatus = jsonObject.get("paymentStatus").getAsString();

            return new Sale(id, vehicleID, customerID, saleDateTime, amountPaid, paymentStatus);
        }catch (JsonParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
