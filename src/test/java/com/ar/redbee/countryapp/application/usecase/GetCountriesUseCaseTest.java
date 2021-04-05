package com.ar.redbee.countryapp.application.usecase;

import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import com.ar.redbee.countryapp.util.StubsFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCountriesUseCaseTest {

    @Mock
    private CountryRepository countryRepository;
    @InjectMocks
    private GetCountriesUseCase getCountriesUseCase;

    @Test
    void ifTheExecuteIsCalledThenShouldRespondAListOfCountries() {
        final var expectedResponse = StubsFactory.buildCountriesList();

        when(countryRepository.getCountries()).thenReturn(expectedResponse);

        final var actualResponse = getCountriesUseCase.execute();

        assertEquals(expectedResponse, actualResponse);
    }
}
