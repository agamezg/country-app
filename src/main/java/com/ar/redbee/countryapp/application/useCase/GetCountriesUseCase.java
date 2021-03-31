package com.ar.redbee.countryapp.application.useCase;

import com.ar.redbee.countryapp.application.domain.Country;
import com.ar.redbee.countryapp.application.port.in.GetCountriesQuery;
import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetCountriesUseCase implements GetCountriesQuery {

    private final CountryRepository countryRepository;

    public GetCountriesUseCase(CountryRepository countryRepository) {

        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> execute() {
        return countryRepository.getCountries();
    }
}
