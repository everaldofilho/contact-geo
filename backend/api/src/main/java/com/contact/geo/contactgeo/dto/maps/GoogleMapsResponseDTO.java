package com.contact.geo.contactgeo.dto.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleMapsResponseDTO {
    @JsonProperty("results")
    private List<GeometryList> results;
}
