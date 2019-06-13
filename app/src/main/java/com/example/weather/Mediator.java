package com.example.weather;


import android.content.Context;

import com.example.weather.FactoryPattern.ApiCall;
import com.example.weather.FactoryPattern.CallFactory;
import com.example.weather.Model.Weather;
import com.example.weather.Model.WeatherContainer;

public class Mediator implements Observer { //front controller, proxy for calling api
    private HttpClient httpClient;
    private CallFactory callFactory;
    public WeatherContainer weatherContainer;
    private Parser parser;
    private MainActivity.ViewUpdater viewUpdater;
    private Weather weather;

    public Mediator(MainActivity.ViewUpdater viewUpdater) {
        weather = new Weather();
        callFactory = new CallFactory();
        parser = new Parser(); //subject
        this.viewUpdater = viewUpdater;
        weatherContainer = new WeatherContainer(parser);
        parser.attach(this);
    }

    public void userRequest(Context context, String callType, String userInput) {
        httpClient = new HttpClient(parser);
        userInput.replace(" ", "");
        String[] args = userInput.split(",", 2);
        int index = weatherContainer.getIfExists(callType, args);
        if (index == -1) {
            ApiCall apiCall = callFactory.getApiCall(callType, args);
            httpClient.connectTo(apiCall.makeRequest());
            httpClient.execute();
            return;
        }
        weather = weatherContainer.getWeather(index);
    }

    public String getCoords() {
        return weather.getCoords();
    }

    public String getCity() {
        return weather.getCity();
    }

    public String getDescription() {
        return weather.getDescription();
    }

    public String getPressure() {
        return weather.getPressure();
    }

    public String getDispHumidity() {
        return weather.getDispHumidity();
    }

    public String getDispTemp() {
        return weather.getDispTemp();
    }

    public String getDispMinTemp() {
        return weather.getDispMinTemp();
    }

    public String getDispMaxTemp() {
        return weather.getDispMaxTemp();
    }

    public String getDispSpeed() {
        return weather.getDispSpeed();
    }

    public String getAll() {
        return weather.getAll();
    }

    public int getCityID() {
        return weather.getCityID();
    }

    @Override
    public void update() {
        weather = parser.getWeather();
        viewUpdater.update();
    }
}
