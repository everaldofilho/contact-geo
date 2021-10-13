package com.contact.geo.contactgeo.controller;

import com.contact.geo.contactgeo.service.GravatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gravatar")
public class GravatarController {

    @Autowired
    public GravatarService gravatarService;

    @GetMapping("{email}")
    public String emailToProfile(@PathVariable("email") String email) throws Exception {
        return gravatarService.getImageURL(email);
    }
}
