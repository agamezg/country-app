package com.ar.redbee.countryapp.adapter.controller;

import com.ar.redbee.countryapp.application.port.in.GetCountriesQuery;
import com.ar.redbee.countryapp.config.TestConfig;
import com.ar.redbee.countryapp.util.StubsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(TestConfig.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetCountriesQuery getCountriesQuery;

    @Test
    void ifTheGetCountriesIsCalledThenShouldReturnAListOfCountries() throws Exception {
        final var expectedResponse = StubsFactory.buildCountriesList();

        when(getCountriesQuery.execute()).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));

        verify(getCountriesQuery).execute();
    }
}