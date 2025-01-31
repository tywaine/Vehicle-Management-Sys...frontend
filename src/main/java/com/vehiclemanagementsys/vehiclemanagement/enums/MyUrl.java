package com.vehiclemanagementsys.vehiclemanagement.enums;

public enum MyUrl {
    API_URL("http://localhost:8081/api/v1/");

    private final String url;

    MyUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}