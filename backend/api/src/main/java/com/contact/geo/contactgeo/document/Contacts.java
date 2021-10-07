package com.contact.geo.contactgeo.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "contacts")
public class Contacts {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Indexed(unique = true)
    private String email;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint geo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    private String photo;
    private String address;
    private Boolean status;
    private String zipcode;
}