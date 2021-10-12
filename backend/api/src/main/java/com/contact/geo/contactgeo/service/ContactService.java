package com.contact.geo.contactgeo.service;

import com.contact.geo.contactgeo.client.GoogleMapsClient;
import com.contact.geo.contactgeo.client.ZipcodeClient;
import com.contact.geo.contactgeo.document.Contacts;
import com.contact.geo.contactgeo.dto.contacts.ContactsCreateDTO;
import com.contact.geo.contactgeo.dto.contacts.ContactsDTO;
import com.contact.geo.contactgeo.dto.contacts.Status;
import com.contact.geo.contactgeo.dto.maps.Location;
import com.contact.geo.contactgeo.repository.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private static final Double RADIUS = 5.0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final ContactsRepository repository;
    private final GravatarService gravatarService;
    private final GoogleMapsClient googleMapsClient;
    private final ZipcodeClient zipcodeClient;

    public ContactsDTO create(ContactsCreateDTO createDTO) throws Exception {

        ContactsDTO contactsDTO = ContactsDTO.builder()
                .email(createDTO.getEmail())
                .name(createDTO.getName())
                .zipcode(createDTO.getZipcode())
                .photo(gravatarService.getImageURL(createDTO.getEmail()))
                .build();

        String address = zipcodeClient.getZipcode(createDTO.getZipcode()).getPublicPlace();
        contactsDTO.setAddress(address);

        Location location = googleMapsClient.getGeo(address).getResults().get(0).getGeometry().getLocation();
        contactsDTO.setLatitude(location.getLat());
        contactsDTO.setLongitude(location.getLng());

        GeoJsonPoint geoJsonPoint = new GeoJsonPoint(contactsDTO.getLongitude(), contactsDTO.getLatitude());

        contactsDTO.setStatus(Status.OK.getValue());

        Contacts contacts = Contacts.builder()
                .address(contactsDTO.getAddress())
                .email(contactsDTO.getEmail())
                .name(contactsDTO.getName())
                .zipcode(contactsDTO.getZipcode())
                .photo(contactsDTO.getPhoto())
                .location(geoJsonPoint)
                .status(contactsDTO.getStatus())
                .build();

        repository.save(contacts);
        return contactsDTO;
    }

    public Page<Contacts> getNear(Double longitude, Double latitude, Double radius,
                                       Integer page, Integer pageSize) {
        radius = radius != null ? radius : RADIUS;
        pageSize = pageSize != null ? pageSize : DEFAULT_PAGE_SIZE;
        Pageable pageable = PageRequest.of(page, pageSize);
        var coordinates = new GeoJsonPoint(longitude, latitude);
        Page<Contacts> result = this.repository.findNearbyContacts(coordinates, radius, pageable);

        return result;
    }

}
