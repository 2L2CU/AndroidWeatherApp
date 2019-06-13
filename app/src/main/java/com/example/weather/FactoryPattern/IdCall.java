package com.example.weather.FactoryPattern;

public class IdCall implements ApiCall {

    String id;

    public IdCall(String id){
        this.id = id;

    }

    @Override
    public String makeRequest() {
        return baseURL + "id=" + id + units +apiKey;
    }
}
