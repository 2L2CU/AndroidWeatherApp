package com.example.weather;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

private boolean stateChanged = false;
private List<Observer> observers = new ArrayList<Observer>();


    public boolean getState() {
        return stateChanged;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
        stateChanged = false;
    }
}
