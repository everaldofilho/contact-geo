package com.contact.geo.contactgeo.controller;

import com.contact.geo.contactgeo.document.Contacts;
import com.contact.geo.contactgeo.dto.contacts.ContactsCreateDTO;
import com.contact.geo.contactgeo.dto.contacts.ContactsDTO;
import com.contact.geo.contactgeo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ContactsDTO create(@RequestBody @Valid ContactsCreateDTO contactDTO) throws Exception {
        ContactsDTO contactsDTO = contactService.create(contactDTO);
        return contactsDTO;
    }

    @GetMapping
    public Page<Contacts> nearby(
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("radius") Double radius,
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize
        ) throws Exception {
        Page<Contacts> results = contactService.getNear(longitude, latitude, radius, page, pageSize);
        return results;
    }
}
