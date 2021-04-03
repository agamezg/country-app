package com.ar.redbee.countryapp.application.port.out;

import com.ar.redbee.countryapp.domain.Country;
import java.util.List;

public interface CountryRepository {

    List<Country> getCountries();
}
