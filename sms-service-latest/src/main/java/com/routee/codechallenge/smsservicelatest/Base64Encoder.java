package com.routee.codechallenge.smsservicelatest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Ramesh.Yaleru on 5/25/2021
 */
public class Base64Encoder {
    public static void main(String[] args) {
        System.out.println(encode("5756a411e4b06a33d50517c7:vb6QpjCIOG"));
    }

    public static String encode(String name) {
        String base64encodedString = null;
        try {
            base64encodedString = Base64.getEncoder().encodeToString(name.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64encodedString;
    }
}
