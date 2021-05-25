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
 * @author Ramesh.Yaleru on 5/25/2021
 */
public class AuthenticationService {
    static String AUTH_BASE_URI="https://auth.routee.net/oauth/token";

    String authenticate(String applicationId, String secrete) throws IOException {
        String accessToken = null;
        StringBuffer sb = new StringBuffer(applicationId);
        String authCode= Base64Encoder.encode(sb.append(":"+secrete).toString());
        URL url = new URL(AUTH_BASE_URI);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Basic " + authCode);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
        wr.writeBytes("grant_type=client_credentials");
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
            accessToken = (String) jsonObject.get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
