package com.contact.geo.contactgeo.dto.searchzipcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZipcodeResponseDTO {
    @JsonProperty("cep")
    private String zipcode;

    @JsonProperty("logradouro")
    private String publicPlace;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String district;

    @JsonProperty("localidade")
    private String location;

    @JsonProperty("uf")
    private String uf;

    @JsonProperty("ibge")
    private String ibge;

    @JsonProperty("gia")
    private String gia;

    @JsonProperty("ddd")
    private String ddd;

    @JsonProperty("siafi")
    private String siafi;
}
