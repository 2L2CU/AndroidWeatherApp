package com.example.weather.Model;

import android.util.Log;

import com.example.weather.Observer;
import com.example.weather.Parser;

import java.util.ArrayList;

public class WeatherContainer implements Observer {

    private ArrayList<Weather> weatherContainer;
    private Parser parser;

    public WeatherContainer(Parser parser){
        weatherContainer = new ArrayList<>();
        this.parser = parser;
        parser.attach(this);
    }

    public void append(Weather w) {
        weatherContainer.add(w);
    }

    public Weather getWeather(int index){
        return weatherContainer.get(index);
    }

    public int getIfExists(String type,String[] args) {
        switch (type) {
            case "COORD":
                for (Weather w: weatherContainer) {
                    if(w.getCoords() == args[0]+","+args[1])
                        return weatherContainer.indexOf(w);
                }break;
            case "NAME":
                for (Weather w: weatherContainer) {
                    if(w.getCity()+","+w.getCountry() == args[0]+","+args[1])
                        return weatherContainer.indexOf(w);
                }break;
            case "ZIP":
                //does not work for zipcode...
                break;
            case "ID":
                for (Weather w: weatherContainer) {
                    if(String.valueOf(w.getCityID()) == args[0]+",")
                        return weatherContainer.indexOf(w);
                }break;
            default:
                Log.d("Not in containter", "sending ApiCall");
        }
        return -1;
    }

    @Override
    public void update() {
        weatherContainer.add(parser.getWeather());
    }
}
