package com.routee.codechallenge.smsservicelatest;

import java.io.IOException;

/**
 * @author Ramesh.Yaleru on 5/24/2021
 */
public class SMSServiceClient {

    static String applicationId="5f9138288b71de3617a87cd3";
    static String secrete = "RSj69jLowJ";

    public String process() throws IOException {
        /****************  1. Authintication  **************************/
        AuthenticationService test = new AuthenticationService();
        String accessToekn = null;
        try{
            accessToekn= test.authenticate(applicationId, secrete);
        }catch (Exception e){
            e.printStackTrace();
            throw  e;
        }
        System.out.println("accessToken --> "+accessToekn);
        /****************  2. call weather API and get temperature  **************************/
        WeatherService weatherService = new WeatherService();
        String location ="Thessaloniki";
        String units= "metric";
        String appId="b385aa7d4e568152288b3c9f5c2458a5";
        Double temperature = weatherService.getWeatherData(location,appId, units);
        System.out.println("temperature"+temperature);
        /****************  3. call weather API and get temperature  **************************/
        SendSMSService smsService = new SendSMSService();
        String response = smsService.sendSMSMessage("ramesh", accessToekn, temperature);

        System.out.println("Final response => "+response);
        return response;
    }
    public static void main(String[] args) throws IOException {
        SMSServiceClient client = new SMSServiceClient();
        client.process();
    }
}
