package com.example.weather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient extends AsyncTask<Void, Void, String>{

    private HttpURLConnection connection;
    private InputStream inputStream;
    private String url;
    private String data;
    Parser parser;

    HttpClient(Parser parser) {
        data = null;
        url = null;
        connection = null;
        inputStream = null;
        this.parser = parser;
    }

    void connectTo(String url){
        this.url = url;
    }

    @Override
    protected String doInBackground(Void... params) {
        setConnection();
        data = readData();
        closeConnection();
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.v("AFTER ASYNC", "proxy data = " + data);
        parser.parse(data);
    }

    private void setConnection() {
        try {
            connection = (HttpURLConnection) (new URL(this.url)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private String readData() {                                       //API response fetched here

        StringBuffer buffer = new StringBuffer();

        try {
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) buffer.append(line).append("rn");
        } catch (Throwable t) {
            t.printStackTrace();
            Log.e("READ_DATA_ERR", t.getMessage());
        }

        return buffer.toString();
    }

    private void closeConnection() {
        try {
            inputStream.close();
            connection.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
            Log.e("CLOSE_CONNECTION_ERROR", t.getMessage());
        }
    }

    String getData() { //uses as get state
        return data;
    }
}