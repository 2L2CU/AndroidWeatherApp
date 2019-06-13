package com.example.weather.Model;

import java.text.MessageFormat;

public class Weather {
    private long sunset, sunrise;
    private String country, city;
    private int cityID,cloudsAll,weatherId;
    private String condition, description, icon;
    private double pressure, humidity,temp, minTemp, maxTemp,speed, deg,longitude, latitude;
    char degreeSymbol = (char) 0x00B0;

    public Weather() { }
    private Weather(WeatherBuilder builder) {
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.cityID = builder.cityId;
        this.sunset = builder.sunset;
        this.sunrise = builder.sunrise;
        this.country = builder.country;
        this.city = builder.city;
        this.weatherId = builder.weatherId;
        this.condition = builder.condition;
        this.description = builder.description;
        this.icon = builder.icon;
        this.pressure = builder.pressure;
        this.humidity = builder.humidity;
        this.temp = builder.temp;
        this.minTemp = builder.minTemp;
        this.maxTemp = builder.maxTemp;
        this.speed = builder.speed;
        this.deg = builder.deg;
        this.cloudsAll =builder.cloudsAll;
    } //private constructor

    public String getCountry() {
        return country;
    }

    public String getCoords(){
        return latitude + "," + longitude;
    }

    public String getDispTemp() {
        if (temp - (int) temp >= 0.5) temp = (int) temp + 1;
        return String.format("%s%s", temp, degreeSymbol);
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getPressure() {
        return MessageFormat.format("{0} hPa", String.valueOf((int) pressure));
    }

    public String getDispMinTemp() {
        if (minTemp - (int) minTemp >= 0.5) minTemp = (int) minTemp + 1;
        return String.format("%s%s", minTemp, degreeSymbol);
    }

    public String getDispMaxTemp() {
        if (maxTemp - (int) maxTemp >= 0.5) maxTemp = (int) minTemp + 1;
        return String.format("%s%s", maxTemp, degreeSymbol);
    }

    public String getDispSpeed() {
        return MessageFormat.format("{0} m/s", String.valueOf(speed));
    }

    public String getAll() {
        return MessageFormat.format("{0} %", String.valueOf(cloudsAll));
    }

    public int getCityID() {
        return cityID;
    }

    public String getDispHumidity() { return MessageFormat.format("{0} %", String.valueOf(humidity));
    }

    //---------------------------------------------------------------------------------
    public static class WeatherBuilder { //BuilderClass

        private long sunset, sunrise;
        private String city,country;
        private int cloudsAll, cityId, weatherId;
        private String condition, description, icon;
        private double pressure, humidity, temp, minTemp, maxTemp, longitude, latitude, speed, deg;

        public WeatherBuilder setCoords(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
            return this;
        }

        public WeatherBuilder setSunset(long sunset) {
            this.sunset = sunset;
            return this;
        }

        public WeatherBuilder setSunrise(long sunrise) {
            this.sunrise = sunrise;
            return this;
        }

        public WeatherBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public WeatherBuilder setWeatherId(int weatherId) {
            this.weatherId = weatherId;
            return this;
        }

        public WeatherBuilder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public WeatherBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public WeatherBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public WeatherBuilder setPressure(double pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder setHumidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder setTemp(double temp) {
            this.temp = temp;
            return this;
        }

        public WeatherBuilder setMinTemp(double minTemp) {
            this.minTemp = minTemp;
            return this;
        }

        public WeatherBuilder setMaxTemp(double maxTemp) {
            this.maxTemp = maxTemp;
            return this;
        }

        public WeatherBuilder setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public WeatherBuilder setDeg(double deg) {
            this.deg = deg;
            return this;
        }

        public WeatherBuilder setCityID(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public WeatherBuilder setCloudsAll(int cloudsAll){
            this.cloudsAll = cloudsAll;
            return this;
        }

        public WeatherBuilder setCountry(String country) { //as country is not always provided for API responce
            this.country = country;
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }
}
