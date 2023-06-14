package com.example;

import com.example.model.response.HottestStarResponse;
import com.example.model.response.OrphanPlanetsResponse;
import com.example.service.DataRetrievalService;
import com.example.service.ExoplanetDataService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MicronautTest
public class ExoplanetDataServiceTest {

    @Inject
    ExoplanetDataService exoplanetDataService;

    @BeforeEach
    void setUp() {
        // Mock the data retrieval service to return a predefined list of exoplanet data
        DataRetrievalService dataRetrievalService = new DataRetrievalService() {
            @Override
            public List<Map<String, Object>> readJsonDataFromUrl() {
                List<Map<String, Object>> exoplanetData = new ArrayList<>();

                // Add sample exoplanet data for testing
                Map<String, Object> exoplanet1 = new HashMap<>();
                exoplanet1.put("TypeFlag", 3);
                exoplanetData.add(exoplanet1);

                Map<String, Object> exoplanet2 = new HashMap<>();
                exoplanet2.put("TypeFlag", 1);
                exoplanetData.add(exoplanet2);

                return exoplanetData;
            }
        };

        exoplanetDataService = new ExoplanetDataService(dataRetrievalService);
    }

    @Test
    void testCountOrphanPlanets() {
        // Act
        OrphanPlanetsResponse orphanPlanetsResponse = exoplanetDataService.countOrphanPlanets();

        // Assert
        Assertions.assertEquals(1, orphanPlanetsResponse.getOrphanPlanetCount());
    }

    @Test
    void testFindHottestStarPlanet() {
        // Act
        HottestStarResponse hottestStarResponse = exoplanetDataService.findHottestStarPlanet();

        // Assert
        Assertions.assertEquals("", hottestStarResponse.getHottestStar());
    }

    @Test
    void testGeneratePlanetTimeline() {
        // Act
        Map<Integer, Map<String, Integer>> planetTimeline = exoplanetDataService.generatePlanetTimeline();

        // Assert
        Assertions.assertEquals(0, planetTimeline.size());
    }

    @Test
    void testDeterminePlanetSize() {
        // Act
        String smallPlanetSize = exoplanetDataService.determinePlanetSize(0.5);
        String mediumPlanetSize = exoplanetDataService.determinePlanetSize(1.5);
        String largePlanetSize = exoplanetDataService.determinePlanetSize(3.0);

        // Assert
        Assertions.assertEquals("small", smallPlanetSize);
        Assertions.assertEquals("medium", mediumPlanetSize);
        Assertions.assertEquals("large", largePlanetSize);
    }

    @Test
    void testCountOrphanPlanets_NoExoplanetData() {
        // Mock data retrieval service to return an empty list
        DataRetrievalService dataRetrievalService = new DataRetrievalService() {
            @Override
            public List<Map<String, Object>> readJsonDataFromUrl() {
                return new ArrayList<>();
            }
        };

        ExoplanetDataService exoplanetDataService = new ExoplanetDataService(dataRetrievalService);

        // Act
        OrphanPlanetsResponse orphanPlanetsResponse = exoplanetDataService.countOrphanPlanets();

        // Assert
        Assertions.assertEquals(0, orphanPlanetsResponse.getOrphanPlanetCount());
    }

    @Test
    void testFindHottestStarPlanet_NoExoplanetData() {
        // Mock data retrieval service to return an empty list
        DataRetrievalService dataRetrievalService = new DataRetrievalService() {
            @Override
            public List<Map<String, Object>> readJsonDataFromUrl() {
                return new ArrayList<>();
            }
        };

        ExoplanetDataService exoplanetDataService = new ExoplanetDataService(dataRetrievalService);

        // Act
        HottestStarResponse hottestStarResponse = exoplanetDataService.findHottestStarPlanet();

        // Assert
        Assertions.assertEquals("", hottestStarResponse.getHottestStar());
    }


    @Test
    void testGeneratePlanetTimeline_InvalidData() {
        // Mock data retrieval service to return exoplanet data with invalid types
        DataRetrievalService dataRetrievalService = new DataRetrievalService() {
            @Override
            public List<Map<String, Object>> readJsonDataFromUrl() {
                List<Map<String, Object>> exoplanetData = new ArrayList<>();

                // Add sample exoplanet data with invalid types
                Map<String, Object> exoplanet1 = new HashMap<>();
                exoplanet1.put("DiscoveryYear", "2020"); // Invalid type
                exoplanet1.put("RadiusJpt", "1.5"); // Invalid type
                exoplanetData.add(exoplanet1);

                return exoplanetData;
            }
        };

        ExoplanetDataService exoplanetDataService = new ExoplanetDataService(dataRetrievalService);

        // Act
        Map<Integer, Map<String, Integer>> planetTimeline = exoplanetDataService.generatePlanetTimeline();

        // Assert
        Assertions.assertEquals(0, planetTimeline.size());
    }
}
