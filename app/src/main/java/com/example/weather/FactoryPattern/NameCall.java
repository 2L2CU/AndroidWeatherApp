package com.example.weather.FactoryPattern;

public class NameCall implements ApiCall {

    String cityName = null;
    String countryCode = null;

    public NameCall(String cityName, String countryCode){
        this.cityName = cityName;
        if (countryCode != null) this.countryCode = countryCode;
    }


    @Override
    public String makeRequest() {
        if (countryCode != null)
        return baseURL + "q=" + cityName + ","+ countryCode + units +apiKey;
        else return baseURL + "q=" + cityName + apiKey;
    }
}
