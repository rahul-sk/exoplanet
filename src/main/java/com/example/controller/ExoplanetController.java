package com.example.controller;

import com.example.constant.URI;
import com.example.model.response.HottestStarResponse;
import com.example.model.response.OrphanPlanetsResponse;
import com.example.service.ExoplanetDataService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

import java.util.Map;

@Controller(URI.BASE_URI)
public class ExoplanetController {

    @Inject
    private ExoplanetDataService exoplanetDataService;


    @Get(value = URI.ORPHAN_COUNT_GET, produces = MediaType.APPLICATION_JSON)
    public OrphanPlanetsResponse getOrphanPlanetCount() {
        return exoplanetDataService.countOrphanPlanets();
    }

    @Get(value = URI.HOTTEST_STAR_PLANET_GET, produces = MediaType.APPLICATION_JSON)
    public HottestStarResponse getPlanetRevolvingHottestStar() {
        return exoplanetDataService.findHottestStarPlanet();
    }

    @Get(value = URI.PLANET_TIMELINE_GET, produces = MediaType.APPLICATION_JSON)
    public Map<Integer, Map<String, Integer>> getPlanetTimeLine() {
        return exoplanetDataService.generatePlanetTimeline();
    }
}


