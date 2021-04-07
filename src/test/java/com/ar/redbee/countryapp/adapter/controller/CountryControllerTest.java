package com.ar.redbee.countryapp.adapter.controller;

import com.ar.redbee.countryapp.application.port.in.GetCountriesInputPort;
import com.ar.redbee.countryapp.config.TestConfig;
import com.ar.redbee.countryapp.util.StubsFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
public class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GetCountriesInputPort getCountriesInputPort;

    @Test
    void ifTheGetCountriesIsCalledThenShouldReturnAListOfCountries() throws Exception {
        final var expectedRestResponse = StubsFactory.buildCountriesRestList();
        final var expectedResponse = StubsFactory.buildCountriesList();

        when(getCountriesInputPort.execute()).thenReturn(expectedResponse);

        mockMvc.perform(get("/api/v1/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedRestResponse)));

        verify(getCountriesInputPort).execute();
    }
}
