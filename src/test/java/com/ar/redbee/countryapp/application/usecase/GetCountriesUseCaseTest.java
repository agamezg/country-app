package com.ar.redbee.countryapp.application.usecase;

import com.ar.redbee.countryapp.application.port.out.CountryOutputPort;
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
    private CountryOutputPort countryOutputPort;
    @InjectMocks
    private GetCountriesUseCase getCountriesUseCase;

    @Test
    void ifTheExecuteIsCalledThenShouldRespondAListOfCountries() {
        final var expectedResponse = StubsFactory.buildCountriesList();

        when(countryOutputPort.getCountries()).thenReturn(expectedResponse);

        final var actualResponse = getCountriesUseCase.execute();

        assertEquals(expectedResponse, actualResponse);
    }
}
