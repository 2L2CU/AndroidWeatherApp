package com.example.weather.FactoryPattern;

import android.util.Log;

public class CallFactory { //factory method

    public ApiCall getApiCall(String callType, String[] args) {
        switch (callType) {
            case "COORD":
                CoordCall coordCall = new CoordCall(args[0], args[1]);
                return coordCall;
            case "NAME":
                NameCall nameCall = new NameCall(args[0], args[1]);
                return nameCall;
            case "ZIP":
                ZipCodeCall zipCodeCall = new ZipCodeCall(args[0], args[1]);
                return zipCodeCall;
            case "ID":
                IdCall idCall = new IdCall(args[0]);
                return idCall;
            default:
                Log.d("FACTORY_FAIL", "wrong argument");
        }
        return null;
    }
}
