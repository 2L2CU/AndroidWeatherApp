package com.example.weather.FactoryPattern;

public class ZipCodeCall implements ApiCall { //works on New York... does not work for Poland :(

    String zipCode;
    String countryCode;

    public ZipCodeCall(String zipCode, String countryCode){
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    @Override
    public String makeRequest() {
        return baseURL + "zip=" + zipCode + "," + countryCode + units + apiKey;
    }
}
