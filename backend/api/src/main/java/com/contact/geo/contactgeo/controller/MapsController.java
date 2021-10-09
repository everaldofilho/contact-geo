package com.contact.geo.contactgeo.controller;

import com.contact.geo.contactgeo.client.GoogleMapsClient;
import com.contact.geo.contactgeo.dto.maps.GoogleMapsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maps")
public class MapsController {

    @Autowired
    public GoogleMapsClient service;

    @GetMapping("{address}")
    public GoogleMapsResponseDTO emailToProfile(@PathVariable("address") String address) {
        return service.getGeo(address);
    }
}
