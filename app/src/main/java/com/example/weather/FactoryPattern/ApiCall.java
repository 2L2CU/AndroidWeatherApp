package com.example.weather.FactoryPattern;

public interface ApiCall {
    String apiKey = "&APPID=6b3e7d54c6575f9fdfdf81b3b1b9c01f";
    String baseURL = "http://api.openweathermap.org/data/2.5/weather?";
    String units = "&units=metric";

    String makeRequest();
}
