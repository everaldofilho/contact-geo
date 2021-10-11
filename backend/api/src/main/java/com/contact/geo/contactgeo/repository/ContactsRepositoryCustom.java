package com.contact.geo.contactgeo.repository;

import com.contact.geo.contactgeo.document.Contacts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


public interface ContactsRepositoryCustom {

    Page<Contacts> findNearbyContacts(GeoJsonPoint coordinates, Double radius, Pageable pageable);
}
