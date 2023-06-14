package com.example.service;

import com.example.model.response.HottestStarResponse;
import com.example.model.response.OrphanPlanetsResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.inject.Singleton;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Service class for retrieving and processing exoplanet data.
 */
@Singleton
public class ExoplanetDataService {

    private DataRetrievalService dataRetrievalService;
    private List<Map<String, Object>> exoplanetData;

    /**
     * Constructs an ExoplanetDataService object.
     *
     * @param dataRetrievalService the data retrieval service used to fetch exoplanet data
     */
    public ExoplanetDataService(DataRetrievalService dataRetrievalService) {
        this.dataRetrievalService = dataRetrievalService;
        this.exoplanetData = dataRetrievalService.readJsonDataFromUrl();
    }

    /**
     * Converts JSON data to a list of maps.
     *
     * @param jsonData the JSON data to convert
     * @return a list of maps representing the converted JSON data
     */
    private List<Map<String, Object>> convertJsonToMap(String jsonData) {
        Gson gson = new Gson();
        Type mapListType = new TypeToken<List<Map<String, Object>>>() {}.getType();
        List<Map<String, Object>> exoplanets = gson.fromJson(jsonData, mapListType);
        return exoplanets;
    }

    /**
     * Counts the number of orphan planets.
     *
     * @return an OrphanPlanetsResponse object containing the count of orphan planets
     */
    public OrphanPlanetsResponse countOrphanPlanets() {
        OrphanPlanetsResponse orphanPlanetsResponse = new OrphanPlanetsResponse();
        int count = 0;
        for (Map<String, Object> exoplanet : exoplanetData) {
            int typeFlag = ((Number) exoplanet.get("TypeFlag")).intValue();
            if (typeFlag == 3) {
                count++;
            }
        }
        orphanPlanetsResponse.setOrphanPlanetCount(count);
        return orphanPlanetsResponse;
    }

    /**
     * Finds the hottest star planet.
     *
     * @return a HottestStarResponse object containing the identifier of the hottest star planet
     */
    public HottestStarResponse findHottestStarPlanet() {
        HottestStarResponse hottestStarResponse = new HottestStarResponse();
        double maxTemperature = Double.MIN_VALUE;
        String planetIdentifier = "";

        for (Map<String, Object> exoplanet : exoplanetData) {
            if (exoplanet.containsKey("HostStarTempK")) {
                Object tempValue = exoplanet.get("HostStarTempK");
                if (tempValue instanceof Number) {
                    double temperature = ((Number) tempValue).doubleValue();
                    if (temperature > maxTemperature) {
                        maxTemperature = temperature;
                        planetIdentifier = (String) exoplanet.get("PlanetIdentifier");
                    }
                }
            }
        }
        hottestStarResponse.setHottestStar(planetIdentifier);
        return hottestStarResponse;
    }

    /**
     * Generates a timeline of planets based on their discovery year and size.
     *
     * @return a map representing the planet timeline, with discovery years as keys and
     *         a nested map of planet sizes and their counts as values
     */
    public Map<Integer, Map<String, Integer>> generatePlanetTimeline() {
        Map<Integer, Map<String, Integer>> planetTimeline = new TreeMap<>();
        for (Map<String, Object> exoplanet : exoplanetData) {
            if (exoplanet.containsKey("DiscoveryYear") && exoplanet.containsKey("RadiusJpt")) {
                Object yearValue = exoplanet.get("DiscoveryYear");
                Object radiusValue = exoplanet.get("RadiusJpt");
                if (yearValue instanceof Number && radiusValue instanceof Number) {
                    int discoveryYear = ((Number) yearValue).intValue();
                    double radius = ((Number) radiusValue).doubleValue();
                    String planetSize = determinePlanetSize(radius);
                    Map<String, Integer> planetSizeCount = planetTimeline.getOrDefault(discoveryYear, new HashMap<>());
                    planetSizeCount.put(planetSize, planetSizeCount.getOrDefault(planetSize, 0) + 1);
                    planetTimeline.put(discoveryYear, planetSizeCount);
                }
            }
        }
        return planetTimeline;
    }

    /**
     * Determines the size of a planet based on its radius.
     *
     * @param radius the radius of the planet
     * @return a string representing the size of the planet
     */
    public String determinePlanetSize(double radius) {
        if (radius < 1) {
            return "small";
        } else if (radius < 2) {
            return "medium";
        } else {
            return "large";
        }
    }
}
