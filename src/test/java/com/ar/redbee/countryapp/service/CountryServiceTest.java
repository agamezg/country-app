package com.ar.redbee.countryapp.service;

import com.ar.redbee.countryapp.repository.CountryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ar.redbee.countryapp.util.StubsFactory.buildCountriesList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Specification for the CountryService")
@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;
    @InjectMocks
    private CountryService countryService;

    @Test
    @DisplayName("When the getCountries method is called" +
            "then should return a list of hispano-speakers countries")
    void ifTheGetCountriesIsCalledThenShouldRespondAListOfCountries() {
        final var expectedResponse = buildCountriesList();

        when(countryRepository.getCountries()).thenReturn(expectedResponse);

        final var actualResponse = countryService.getCountries();

        assertEquals(expectedResponse, actualResponse);
    }
}
