package com.ar.redbee.countryapp.repository;

import com.ar.redbee.countryapp.config.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.ar.redbee.countryapp.repository.CountryRepository.ES_COUNTRIES;
import static com.ar.redbee.countryapp.util.StubsFactory.buildCountriesList;
import static com.ar.redbee.countryapp.util.StubsFactory.loadJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Import(TestConfig.class)
@RestClientTest(CountryRepository.class)
@DisplayName("Specification for the CountryRepository")
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private MockRestServiceServer mockedServer;

    @Test
    @DisplayName("When the getCountries method is called " +
            "then should return a list of hispano-speakers countries")
    void ifTheGetCountriesIsCalledShouldReturnAListOfCountries() throws IOException {
        final var expectedServerResponse = loadJson("contract/get-hispano-speakers-countries.json");
        final var expectedRepositoryResponse = buildCountriesList();

        this.mockedServer.expect(requestTo(ES_COUNTRIES))
                .andRespond(withSuccess(expectedServerResponse, MediaType.APPLICATION_JSON));

        final var actualResponse = countryRepository.getCountries();

        assertEquals(expectedRepositoryResponse, actualResponse);
    }
}
