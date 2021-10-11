package com.contact.geo.contactgeo.repository;

import com.contact.geo.contactgeo.document.Contacts;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class ContactsRepositoryCustomImpl implements ContactsRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "contacts";

    @Override
    public Page<Contacts> findNearbyContacts(GeoJsonPoint coordinates, Double radius, Pageable pageable) {
        Query query = new Query();
        NearQuery nearQuery = NearQuery.near(coordinates)
                .maxDistance(radius, Metrics.KILOMETERS)
                .query(query);

        List<AggregationOperation> aggregationList = getAggregationOperations(pageable, nearQuery);

        TypedAggregation<ContactAggregationResult> aggregation = TypedAggregation.newAggregation(ContactAggregationResult.class,
                aggregationList);

        ContactAggregationResult aggregationResult = mongoTemplate.aggregate(aggregation, COLLECTION_NAME, ContactAggregationResult.class)
                .getUniqueMappedResult();

        if (aggregationResult == null ) {
            return new PageImpl<>(List.of(), pageable, 0);
        }

        return new PageImpl<>(List.of(), pageable, aggregationResult.getTotal());
    }

    private List<AggregationOperation> getAggregationOperations(Pageable pageable, NearQuery nearQuery) {
        GeoNearOperation geoNear = Aggregation.geoNear(nearQuery, "distance");

        AggregationOperation group = Aggregation.group().count().as("total")
                .addToSet(pageable.getPageNumber()).as("pageNumber")
                .addToSet(pageable.getPageSize()).as("pageSize")
                .addToSet(pageable.getOffset()).as("offset")
                .push("$$ROOT").as("data");

        AggregationOperation project = Aggregation.project()
                .andInclude("pageSize", "pageNumber", "total", "offset")
                .and(ArrayOperators.Slice.sliceArrayOf("data").offset((int) pageable.getOffset()).itemCount(pageable.getPageSize()))
                .as("data");

        return Arrays.asList(geoNear, group, project);
    }

}

@Data
class ContactAggregationResult {

    private long pageSize;
    private long pageNumber;
    private long offset;
    private long total;

}