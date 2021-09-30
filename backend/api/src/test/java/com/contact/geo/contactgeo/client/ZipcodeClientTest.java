package com.contact.geo.contactgeo.client;

import com.contact.geo.contactgeo.dto.searchzipcode.ZipcodeResponseDTO;
import com.contact.geo.contactgeo.exception.ZipcodeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ZipcodeClientTest {

    @MockBean
    ZipcodeClient zipcodeClient;

    @Test
    @DisplayName("deve retornar ZipcodeResponseDTO quando a localização for encontrado.")
    public void shouldReturnZipcodeResponseDTOWhenLocationally() {
        var validZipcode = "59032-250";
        var zipcodeResponseDTO = makeZipcodeResponseDTO();
        Mockito.when(zipcodeClient.getZipcode(validZipcode)).thenReturn(zipcodeResponseDTO);
        var clientResponse = zipcodeClient.getZipcode(validZipcode);

        assertThat(clientResponse.getGia()).isEmpty();
        assertThat(clientResponse.getZipcode()).isEqualTo(validZipcode);
        assertThat(clientResponse.getComplement()).isEqualTo(zipcodeResponseDTO.getComplement());
        assertThat(clientResponse.getDdd()).isEqualTo(zipcodeResponseDTO.getDdd());
        assertThat(clientResponse.getLocation()).isEqualTo(zipcodeResponseDTO.getLocation());
        assertThat(clientResponse.getDistrict()).isEqualTo(zipcodeResponseDTO.getDistrict());
        assertThat(clientResponse.getIbge()).isEqualTo(zipcodeResponseDTO.getIbge());
        assertThat(clientResponse.getUf()).isEqualTo(zipcodeResponseDTO.getUf());
        assertThat(clientResponse.getSiafi()).isEqualTo(zipcodeResponseDTO.getSiafi());
    }

    @Test
    @DisplayName("deve retornar erro se o cep for invalido")
    public void shouldReturnErrorIfZipcodeIsInvalid() {
        Mockito.when(zipcodeClient.getZipcode(Mockito.anyString())).thenThrow(new ZipcodeException());
        Throwable exception = Assertions.catchThrowable(() -> zipcodeClient.getZipcode(Mockito.anyString()));

        assertThat(exception)
                .isInstanceOf(ZipcodeException.class)
                .hasMessage(ZipcodeException.MESSAGE);
    }

    private ZipcodeResponseDTO makeZipcodeResponseDTO() {
        return ZipcodeResponseDTO
                .builder()
                .publicPlace("Vila Maurício")
                .ddd("84")
                .zipcode("59032-250")
                .gia("")
                .complement("de 31/32 ao fim")
                .ibge("2408102")
                .uf("RN")
                .location("Natal")
                .district("Lagoa Seca")
                .siafi("1761")
                .build();
    }
}
