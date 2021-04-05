package com.ar.redbee.countryapp.controller;

import com.ar.redbee.countryapp.config.TestConfig;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.ar.redbee.countryapp.util.StubsFactory.buildCountriesList;
import static com.ar.redbee.countryapp.util.StubsFactory.loadJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
        final var expectedResponse = buildCountriesList();
        final var expectedSerializedResponse = loadJson("contract/get-countries.json");

        when(countryService.getCountries()).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(expectedSerializedResponse, objectMapper.writeValueAsString(expectedResponse));

        verify(countryService, times(1)).getCountries();
    }
}
