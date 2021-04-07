package com.ar.redbee.countryapp.application.usecase;

import com.ar.redbee.countryapp.adapter.controller.mapper.CountryMapper;
import com.ar.redbee.countryapp.adapter.controller.model.CountryRest;
import com.ar.redbee.countryapp.config.UseCase;
import com.ar.redbee.countryapp.application.port.in.GetCountriesInputPort;
import com.ar.redbee.countryapp.application.port.out.CountryOutputPort;
import com.ar.redbee.countryapp.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class GetCountriesUseCase implements GetCountriesInputPort {

    private final CountryOutputPort countryOutputPort;

    @Autowired
    public GetCountriesUseCase(CountryOutputPort countryOutputPort) {
        this.countryOutputPort = countryOutputPort;
    }

    @Override
    public List<Country> execute() {
        return countryOutputPort.getCountries();
    }
}
