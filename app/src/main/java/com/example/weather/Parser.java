package com.example.weather;

import android.util.Log;

import com.example.weather.Model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser extends Observable {
    private Weather weather;

    private JSONObject coordObj;
    private JSONObject sysObj;
    private JSONObject mainObj;
    private JSONObject jsonWeather;
    private JSONObject windObject;
    private JSONObject cloudObj;
    private JSONObject fullData;
    private JSONArray jsonArray;
    public Parser() {
        coordObj = new JSONObject();
        sysObj = new JSONObject();
        mainObj = new JSONObject();
        jsonWeather = new JSONObject();
        windObject = new JSONObject();
        cloudObj = new JSONObject();
        jsonArray = new JSONArray();
    }

    public Weather parse(String apiResponse) {
        try {
            fullData = new JSONObject(apiResponse);
            jsonArray = fullData.getJSONArray("weather");
            coordObj = fullData.getJSONObject("coord");
            sysObj = fullData.getJSONObject("sys");
            mainObj = fullData.getJSONObject("main");
            jsonWeather = jsonArray.getJSONObject(0);
            windObject = fullData.getJSONObject("wind");
            cloudObj = fullData.getJSONObject("clouds");
        } catch (JSONException jsonExeption) {
            Log.v("JSONExeption", "error while parsing response");
            return null;
        }
        makeWeatherObject();
        notifyAllObservers();
        return weather;
    }

    void makeWeatherObject() {
        try {
            Log.d("PARSER", "starting to parse weather");
            weather = new Weather.WeatherBuilder()
                    .setCoords(coordObj.getDouble("lon"), coordObj.getDouble("lat"))
                    .setSunset(sysObj.getLong("sunset"))
                    .setSunrise(sysObj.getLong("sunrise"))
                    .setCountry(sysObj.getString("country")) // not always provided in apiResponce
                    .setCity(fullData.getString("name"))
                    .setCityID(sysObj.getInt("id"))
                    .setWeatherId(jsonWeather.getInt("id"))
                    .setDescription(jsonWeather.getString("description"))
                    .setCondition(jsonWeather.getString("icon"))
                    .setHumidity(mainObj.getInt("humidity"))
                    .setPressure(mainObj.getDouble("pressure"))
                    .setMaxTemp(mainObj.getDouble("temp_max"))
                    .setMinTemp(mainObj.getDouble("temp_min"))
                    .setTemp(mainObj.getDouble("temp"))
                    .setSpeed(windObject.getDouble("speed"))
                    //.setDeg(windObject.getDouble("deg"))
                    .setCloudsAll(cloudObj.getInt("all"))
                    .build();
        } catch (JSONException exeption) {
            System.out.println("JSON EXEPTION: City and Country not provided in API responce");
            weather = makeOceanWeatherObject();
        }

    }

    Weather makeOceanWeatherObject(){
        try{
            weather = new Weather.WeatherBuilder()
                    .setCoords(coordObj.getDouble("lon"), coordObj.getDouble("lat"))
                    .setSunset(sysObj.getLong("sunset"))
                    .setSunrise(sysObj.getLong("sunrise"))
                    .setWeatherId(jsonWeather.getInt("id"))
                    .setDescription(jsonWeather.getString("description"))
                    .setCondition(jsonWeather.getString("icon"))
                    .setHumidity(mainObj.getInt("humidity"))
                    .setPressure(mainObj.getDouble("pressure"))
                    .setMaxTemp(mainObj.getDouble("temp_max"))
                    .setMinTemp(mainObj.getDouble("temp_min"))
                    .setTemp(mainObj.getDouble("temp"))
                    .setSpeed(windObject.getDouble("speed"))
                    .setDeg(windObject.getDouble("deg"))
                    .setCloudsAll(cloudObj.getInt("all"))
                    .build();
        }catch (JSONException exeption){ Log.e("JSON:", "unknown error"); }
        return weather;
    }

    public Weather getWeather() {
        return weather;
    } //accessing weather object from another class

}
