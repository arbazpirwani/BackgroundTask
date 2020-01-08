package com.example.backgroundtask.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

abstract public class Utility {

    public static String callAnApi(String uri) {
        StringBuilder responseString = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(uri).openStream()
                    )
            );

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                responseString.append(inputLine);
            }
            reader.close();

        } catch (Exception ex) {
            return ex.getClass().getSimpleName();
        }

        return responseString.toString();
    }

}
