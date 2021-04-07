package com.ar.redbee.countryapp.application.port.in;

import com.ar.redbee.countryapp.domain.Country;

import java.util.List;

public interface GetCountriesInputPort {
    List<Country> execute();
}
