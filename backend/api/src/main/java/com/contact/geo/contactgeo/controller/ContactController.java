package com.contact.geo.contactgeo.controller;

import com.contact.geo.contactgeo.document.Contacts;
import com.contact.geo.contactgeo.dto.contacts.ContactsCreateDTO;
import com.contact.geo.contactgeo.dto.contacts.ContactsDTO;
import com.contact.geo.contactgeo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public Page<Contacts> nearby() throws Exception {
        Page<Contacts> contactsDTO = contactService.nearby( -46.3753045, -23.5576921, 500000D,0, 10);
        return contactsDTO;
    }
}
