package com.contact.geo.contactgeo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class GravatarServiceTest {

    private static String URL_EXPECTED = "https://www.gravatar.com/avatar/64505d93b19ff2f96af666d73c2bbede?d=monsterid";
    private GravatarService gravatarService;

    @BeforeEach
    public void setUp() {
        this.gravatarService = new GravatarService();
    }

    @Test
    @DisplayName("Gerando url, com email simples")
    public void shouldReturnImageUrlTest() throws Exception {
        Assertions
                .assertThat(gravatarService.getImageURL("everaldo.webmail@gmail.com"))
                .isEqualTo(URL_EXPECTED);
    }

    @Test
    @DisplayName("Gerando url, com email com espaços")
    public void shouldReturnImageUrlEmailWithSpaceTest() throws Exception {
        Assertions
                .assertThat(gravatarService.getImageURL("everaldo.webmail@gmail.com   "))
                .isEqualTo(URL_EXPECTED);
    }

    @Test
    @DisplayName("Gerando url, com email contendo espaços e letra maiuscula.")
    public void shouldReturnImageUrlEmailWithSpaceAndToUpperTest() throws Exception {
        Assertions
                .assertThat(gravatarService.getImageURL(" EveRalDo.weBmail@gMail.cOm   "))
                .isEqualTo(URL_EXPECTED);

    }

    @Test
    @DisplayName("Deve gerar exception quando não informado email")
    public void shoudReturnExceptionEmailNullOrBlankTest() {
        GravatarService gravatarService = new GravatarService();

        assertThrows(IllegalArgumentException.class, () -> gravatarService.getImageURL(null));
        assertThrows(IllegalArgumentException.class, () -> gravatarService.getImageURL(""));
    }

}
