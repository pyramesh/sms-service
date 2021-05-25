package com.routee.codechallenge.smsservicelatest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Ramesh.Yaleru on 5/24/2021
 */
public class WeatherService implements IWeatherService{

    @Override
    public Double getWeatherData(String location, String appId, String units) throws IOException {
        Double temparature=null;
        URL url = new URL(new StringBuffer(apiWeatherAPiBase).append(location).append("&units="+units+"&appid="+appId).toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        wr.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse(response.toString());
            JSONObject mainObject = (JSONObject) jsonObject.get("main");
            temparature=(Double)  mainObject.get("temp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temparature;
    }
}

