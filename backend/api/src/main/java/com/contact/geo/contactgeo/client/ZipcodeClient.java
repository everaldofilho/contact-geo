package com.contact.geo.contactgeo.client;

import com.contact.geo.contactgeo.dto.searchzipcode.ZipcodeResponseDTO;
import com.contact.geo.contactgeo.exception.ZipcodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ZipcodeClient {

    @Value("${app.cep-client.uri}")
    private String uri;

    private final WebClient webClient;

    public ZipcodeClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public ZipcodeResponseDTO getZipcode(String zipcode) {
        return webClient
                .method(HttpMethod.GET)
                .uri(uri, zipcode)
                .retrieve()
                .bodyToMono(ZipcodeResponseDTO.class)
                .doOnError(this::handleError)
                .block();
    }

    private void handleError(Throwable throwable) {
        throw new ZipcodeException();
    }
}
