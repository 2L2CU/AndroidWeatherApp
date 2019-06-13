package com.example.weather.FactoryPattern;

public class CoordCall implements ApiCall {

    String longitude;
    String latitude;

    public CoordCall(String latitude, String longitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }



    @Override
    public String makeRequest() {
        return baseURL + "lat=" + latitude + "&" + "lon="+ longitude+ units +apiKey;
    }
}
