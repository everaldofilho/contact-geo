package com.contact.geo.contactgeo.dto.contacts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactsCreateDTO {

    @NotEmpty(message = "the email cannot be null")
    private String email;

    @NotEmpty(message = "the name cannot be null")
    private String name;

    @NotEmpty(message = "the zipcode cannot be null")
    private String zipcode;

}
