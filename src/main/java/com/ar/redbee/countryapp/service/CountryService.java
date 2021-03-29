package com.ar.redbee.countryapp.service;

import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.getCountries();
    }
}
