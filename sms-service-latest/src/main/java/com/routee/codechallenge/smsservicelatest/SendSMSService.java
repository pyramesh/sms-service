package com.routee.codechallenge.smsservicelatest;

import okhttp3.*;

import java.io.IOException;

/**
 * @author Ramesh.Yaleru on 5/25/2021
 */
public class SendSMSService {
    static String SMS_BASE_URI="https://connect.routee.net/sms";
    static String SENDER = "pyramesh";
    static long THRESHOLD_TEMP = 20;
    public String sendSMSMessage(String name, String accessToken, double temperature) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        String message = null;
        String mobileNumber =null;
        //TODO: scope for refactor
        if(temperature >THRESHOLD_TEMP){
            message = " and Temperature more than 20C."+temperature;
            mobileNumber="+30 6978745957";
        }else{
            message = " and Temperature less than 20C."+temperature;
            mobileNumber="+30  6978745957";
        }
        String finalMsg = new StringBuffer(name).append(message).toString();
        String finalMessage = "{\"body\":\"" + finalMsg + "\", \"to\":\"" + mobileNumber + "\", \"from\":\"" + SENDER + "\"}";
        RequestBody body = RequestBody.create(mediaType, finalMessage);
        Request request = new Request.Builder()
                .url(SMS_BASE_URI)
                .post(body)
                .addHeader("authorization", "Bearer "+accessToken)
                .addHeader("content-type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return response.code() == 200 ? "SUCCESS": "FAILED";
    }
}
