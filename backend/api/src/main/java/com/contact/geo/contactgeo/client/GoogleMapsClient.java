package com.contact.geo.contactgeo.client;

import com.contact.geo.contactgeo.dto.maps.GoogleMapsResponseDTO;
import com.contact.geo.contactgeo.exception.GoogleMapsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GoogleMapsClient {

    @Value("${app.maps.uri}")
    private String uri;
    @Value("${app.maps.key}")
    private String key;

    private final WebClient webClient;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public GoogleMapsClient() {
        this.webClient = WebClient.create();
    }

    public GoogleMapsResponseDTO getGeo(String address) {
        System.out.println(this.uri);
        return webClient
                .method(HttpMethod.GET)
                .uri(this.uri, address, this.key)
                .retrieve()
                .bodyToMono(GoogleMapsResponseDTO.class)
                .doOnError(this::handleError)
                .block();
    }

    private void handleError(Throwable throwable) {
        throw new GoogleMapsException();
    }
}
