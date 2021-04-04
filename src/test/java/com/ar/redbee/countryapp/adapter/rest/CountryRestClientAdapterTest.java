package com.ar.redbee.countryapp.adapter.rest;

import com.ar.redbee.countryapp.config.TestConfig;
import com.ar.redbee.countryapp.util.StubsFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Import(TestConfig.class)
@RestClientTest(CountryRestClientAdapter.class)
public class CountryRestClientAdapterTest {

    @Autowired
    private CountryRestClientAdapter countryRestClientAdapter;
    @Autowired
    private MockRestServiceServer mockedServer;

    @Test
    @DisplayName("When the getCountries method is called " +
            "then should return a list of hispano-speakers countries")
    void ifTheGetCountriesIsCalledShouldReturnAListOfCountries() throws IOException {
        var expectedRepositoryResponse = StubsFactory.buildCountriesList();
        var expectedServerResponse = loadJson("contract/get-hispano-speakers-countries.json");

        this.mockedServer.expect(requestTo("https://restcountries.eu/rest/v2/lang/es"))
                .andRespond(withSuccess(expectedServerResponse, MediaType.APPLICATION_JSON));

        var actualResponse = countryRestClientAdapter.getCountries();
        assertEquals(expectedRepositoryResponse, actualResponse);
    }

    private String loadJson(String path) throws IOException {
        final var resourceLoader = new DefaultResourceLoader();
        final var resource = resourceLoader.getResource(path);
        final var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name());
        return FileCopyUtils.copyToString(reader);
    }
}
