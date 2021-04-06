package com.ar.redbee.countryapp.repository;

import com.ar.redbee.countryapp.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class CountryRepository {

    public final static String ES_COUNTRIES = "https://restcountries.eu/rest/v2/lang/es";
    private final RestTemplate restTemplate;

    @Autowired
    public CountryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        log.info("Se procede a buscar países de habla hispana {}", ES_COUNTRIES);
        return Optional.ofNullable(restTemplate.getForObject(URI.create(ES_COUNTRIES), Country[].class))
                .map(List::of)
                .orElse(List.of());
    }
}
