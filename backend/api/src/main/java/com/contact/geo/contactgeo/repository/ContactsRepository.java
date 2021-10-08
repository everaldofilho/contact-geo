package com.contact.geo.contactgeo.repository;

import com.contact.geo.contactgeo.document.Contacts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends MongoRepository<Contacts, String> {
}
