package com.contact.geo.contactgeo.client;

import com.contact.geo.contactgeo.dto.maps.Geometry;
import com.contact.geo.contactgeo.dto.maps.GeometryList;
import com.contact.geo.contactgeo.dto.maps.GoogleMapsResponseDTO;
import com.contact.geo.contactgeo.dto.maps.Location;
import com.contact.geo.contactgeo.exception.GoogleMapsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import net.minidev.json.JSONValue;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class GoogleMapsClientTest extends AbstractClientTest{

    GoogleMapsClient googleMapsClient;

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());

        googleMapsClient = new GoogleMapsClient();
        googleMapsClient.setUri(baseUrl.concat("/maps/api/geocode/json?address={address}&key={key}"));
    }

    @Test
    @DisplayName("Deve retornar Geo quando a localização for encontrado.")
    public void shouldReturnZipcodeResponseDTOWhenLocationally() {
        String address = "Rua joaozinho";

        mockBackEnd.enqueue(new MockResponse()
                .setBody(getJsonFileAsString("data/google-maps/geocode-200.json"))
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpResponseStatus.OK.code())
        );

        GoogleMapsResponseDTO geo = googleMapsClient.getGeo(address);
        Location locationResponse = geo.getResults().get(0).getGeometry().getLocation();

        Assertions.assertThat(locationResponse.getLat()).isEqualTo(-23.5613807);
        Assertions.assertThat(locationResponse.getLng()).isEqualTo(-46.3577364);
    }

    @Test
    @DisplayName("deve retornar erro quando o endereço for invalido")
    public void shouldReturnErrorIfZipcodeIsInvalid() {
        mockBackEnd.enqueue(new MockResponse()
                .setBody("{}")
                .addHeader("Content-Type", "application/json")
                .setResponseCode(HttpResponseStatus.BAD_REQUEST.code())
        );

        String address = "Rua joaozinho de oliveira";

        Throwable exception = Assertions.catchThrowable(() -> googleMapsClient.getGeo(address));

        assertThat(exception)
                .isInstanceOf(GoogleMapsException.class)
                .hasMessage(GoogleMapsException.MESSAGE);
    }
}
