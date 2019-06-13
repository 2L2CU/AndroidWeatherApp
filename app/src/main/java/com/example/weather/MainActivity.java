package com.example.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView temperature, city, maxTemp, minTemp;
    TextView clouds, pressure, humidity, wind, weatherDescp;
    EditText coords, cityCountry, zipcodeCountry;

    Mediator mediator;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        temperature = findViewById(R.id.temperature_text_view);
        city = findViewById(R.id.city_text_view);
        maxTemp = findViewById(R.id.max_temp_text_view);
        minTemp = findViewById(R.id.min_temp_text_view);
        clouds = findViewById(R.id.clouds_state);
        pressure = findViewById(R.id.pressure_state);
        humidity = findViewById(R.id.humidity_state);
        wind = findViewById(R.id.wind_speed);
        weatherDescp = findViewById(R.id.weather_description);

        coords = findViewById(R.id.coords_edittext);
        cityCountry = findViewById(R.id.city_name_edittext);
        zipcodeCountry = findViewById(R.id.zip_code);

        ViewUpdater viewUpdater = new ViewUpdater(this);
        mediator = new Mediator(viewUpdater);

        if (bundle != null) {
            if (bundle.getInt("locID") != 0) {
                String idCallInput = String.valueOf(bundle.getInt("locID")).concat(",");
                mediator.userRequest(this, "ID", idCallInput);
            } else
                mediator.userRequest(this, "COORD", bundle.getString("COORDS"));

            displayWeather();
        }
    }


        @Override
        protected void onSaveInstanceState (Bundle outState){
            super.onSaveInstanceState(outState);
            outState.putInt("locID", mediator.getCityID());
            outState.putString("COORDS", mediator.getCoords());
        }

        public void makeCoordCall (View view){
            if (simpleVerify(coords, 3) != 0)
                return;
            mediator.userRequest(this, "COORD", coords.getText().toString());
        }

        public void makeCitynameCall (View view){
            if (simpleVerify(cityCountry, 5) != 0)
                return;
            mediator.userRequest(this, "NAME", cityCountry.getText().toString());
        }

        public void makeZipCodeCall (View view){
            if (simpleVerify(zipcodeCountry, 8) != 0)
                return;
            mediator.userRequest(this, "ZIP", zipcodeCountry.getText().toString());
        }

        public void displayWeather () { //
                temperature.setText(mediator.getDispTemp());
                city.setText(mediator.getCity());
                maxTemp.setText(mediator.getDispMaxTemp());
                minTemp.setText(mediator.getDispMinTemp());
                weatherDescp.setText(mediator.getDescription());
                pressure.setText(mediator.getPressure());
                humidity.setText(mediator.getDispHumidity());
                wind.setText(mediator.getDispSpeed());
                clouds.setText(mediator.getAll());

        }

        public int simpleVerify (EditText editText,int minLength){
            String myString = editText.getText().toString();
            myString.replace(" ", "");
            if (myString.length() < minLength) {
                Toast.makeText(this, "Your input is too short", Toast.LENGTH_SHORT).show();
                return -1;
            }
            if (!myString.contains(",")) {
                Toast.makeText(this, "Separate arguments with , <comma> sign", Toast.LENGTH_SHORT).show();
                return -1;
            }
            if (myString.indexOf(",") != myString.lastIndexOf(",")) {
                Toast.makeText(this, "float numbers user . <dot> ", Toast.LENGTH_SHORT).show();
                return -1;
            }
            return 0;
        }

        public class ViewUpdater{
            MainActivity reference;

            ViewUpdater(MainActivity reference){
                this.reference = reference;
            }
            public void update(){
                displayWeather();
            }
        }
    }
