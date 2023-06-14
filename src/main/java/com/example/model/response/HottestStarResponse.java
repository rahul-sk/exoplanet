package com.example.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hottest Star DTO Record, used as an entity class.
 *
 * @author <a href="mailto:koimatturrahul704@gmail.com">Rahul S Koimattur </a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HottestStarResponse {

    @JsonProperty("HottestStarPlanet")
    public String hottestStar;
}
