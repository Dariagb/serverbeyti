package ru.daria.serverbeyti.feignKlient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
public class OxideClientTest {

    @Autowired
    private OxideClient oxideClient;

    @Test
    void calculateOxideForPaint_whenValidVolume_returnValidResponse() throws Exception {
        Long volume = 10L;

        stubFor(get(urlEqualTo("/demo/" + volume))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("42")));

        Long result = oxideClient.calculateOxideForPaint(volume);

        assertThat(result).isEqualTo(600L);
    }
}

