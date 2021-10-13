package com.contact.geo.contactgeo.repository;

import com.contact.geo.contactgeo.document.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ContactsRepositoryCustomImpl implements ContactsRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Contacts> findNearbyContacts(GeoJsonPoint coordinates, Double radius, Pageable pageable) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();

        Query query = new Query().with(pageable);

        NearQuery nearQuery  = NearQuery
                .near(coordinates, Metrics.KILOMETERS)
                .maxDistance(radius)
                .query(query);

        list.add(Aggregation.geoNear(nearQuery, "distance"));
        list.add(Aggregation.project("id","photo", "email", "name", "address", "status", "zipcode", "distance", "location"));

        TypedAggregation<Contacts> agg = Aggregation.newAggregation(Contacts.class, list);

        List<Contacts> result = mongoTemplate.aggregate(agg, Contacts.class).getMappedResults();

        if (result == null || result.size() <= 0) {
            new PageImpl<>(List.of(), pageable, 0);
        }
        return new PageImpl<>(result, pageable, 0);
    }
}