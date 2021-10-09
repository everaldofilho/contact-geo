package com.contact.geo.contactgeo.dto.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeometryList {

    @JsonProperty("geometry")
    public Geometry geometry;
}
