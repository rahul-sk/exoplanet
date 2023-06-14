package com.example.service;

import com.example.constant.ServiceConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.inject.Singleton;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Service class for retrieving JSON data from a URL and converting it to a list of maps.
 */
@Singleton
public class DataRetrievalService {

    /**
     * Reads JSON data from a URL and converts it to a list of maps.
     *
     * @return a list of maps representing the converted JSON data
     */
    public List<Map<String, Object>> readJsonDataFromUrl() {
        String url = ServiceConstants.EXOPLANET_DATA_URL;
        StringBuilder jsonData = new StringBuilder();
        try {
            URL dataUrl = new URL(url);
            // Open a connection and create a BufferedReader to read the data from the URL
            BufferedReader reader = new BufferedReader(new InputStreamReader(dataUrl.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type mapListType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        // Parse the JSON data into a list of maps representing the exoplanets
        List<Map<String, Object>> exoplanets = gson.fromJson(jsonData.toString(), mapListType);
        return exoplanets;
    }
}
