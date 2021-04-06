package com.ar.redbee.countryapp.application.usecase;

import com.ar.redbee.countryapp.config.UseCase;
import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.application.port.in.GetCountriesQuery;
import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class GetCountriesUseCase implements GetCountriesQuery {

    private final CountryRepository countryRepository;

    @Autowired
    public GetCountriesUseCase(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> execute() {
        return countryRepository.getCountries();
    }
}
