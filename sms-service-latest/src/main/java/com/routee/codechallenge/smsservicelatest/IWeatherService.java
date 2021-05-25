package com.routee.codechallenge.smsservicelatest;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author Ramesh.Yaleru on 5/24/2021
 */
public interface IWeatherService {
    String apiWeatherAPiBase="http://api.openweathermap.org/data/2.5/weather?q=";
    Double getWeatherData(String location, String appId, String units) throws IOException;
}
