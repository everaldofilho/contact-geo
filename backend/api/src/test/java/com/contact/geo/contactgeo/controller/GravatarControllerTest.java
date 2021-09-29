package com.contact.geo.contactgeo.controller;

import com.contact.geo.contactgeo.service.GravatarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
public class GravatarControllerTest {

    String RETURN_EXPECTED = "https://www.gravatar.com/avatar/23463b99b62a72f26ed677cc556c44e8";
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    GravatarService service;

    @Test
    @DisplayName("Deve retorna a url do gravatar")
    void getProfileURLTest() throws Exception {
        String email = "example@example.com";
        Mockito.when(service.getImageURL(email)).thenReturn(RETURN_EXPECTED);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/gravatar/".concat(email));

        mockMvc.perform(request.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(RETURN_EXPECTED));
    }
}
