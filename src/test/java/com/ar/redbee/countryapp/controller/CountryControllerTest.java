package com.ar.redbee.countryapp.controller;

import com.ar.redbee.countryapp.config.TestConfig;
import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
@DisplayName("Specification for the CountryController")
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CountryService countryService;


    @Test
    @DisplayName("When the GET /countries endpoint is called " +
            "then should return the list of the countries")
    void ifTheGetCountriesIsCalledThenShouldReturnAListOfCountries() throws Exception {
        final var expectedResponse = buildExpectedResponse();

        when(countryService.getCountries()).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

        verify(countryService, times(1)).getCountries();
    }

    private List<Country> buildExpectedResponse() {
        return List.of(
                Country.builder()
                        .name("Argentina")
                        .alpha2Code("AR")
                        .alpha3Code("ARG")
                        .capital("Buenos Aires")
                        .build(),
                Country.builder()
                        .name("Belize")
                        .alpha2Code("BZ")
                        .alpha3Code("BLZ")
                        .capital("Belmopan")
                        .build(),
                Country.builder()
                        .name("Bolivia (Plurinational State of)")
                        .alpha2Code("BO")
                        .alpha3Code("BOL")
                        .capital("Sucre")
                        .build(),
                Country.builder()
                        .name("Chile")
                        .alpha2Code("CL")
                        .alpha3Code("CHL")
                        .capital("Santiago")
                        .build(),
                Country.builder()
                        .name("Colombia")
                        .alpha2Code("CO")
                        .alpha3Code("COL")
                        .capital("Bogotá")
                        .build(),
                Country.builder()
                        .name("Costa Rica")
                        .alpha2Code("CR")
                        .alpha3Code("CRI")
                        .capital("San José")
                        .build(),
                Country.builder()
                        .name("Cuba")
                        .alpha2Code("CU")
                        .alpha3Code("CUB")
                        .capital("Havana")
                        .build()
        );
    }

    private String loadJson(String path) throws IOException {
        final var resourceLoader = new DefaultResourceLoader();
        final var resource = resourceLoader.getResource(path);
        final var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name());
        return FileCopyUtils.copyToString(reader);
    }
}
