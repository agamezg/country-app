package com.ar.redbee.countryapp.application.port.in;

import com.ar.redbee.countryapp.application.domain.Country;

import java.util.List;

public interface GetCountriesQuery {
    List<Country> execute();
}
