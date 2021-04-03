package com.ar.redbee.countryapp.adapter.rest;

import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CountryRestAdapter implements CountryRepository {

    private final RestTemplate restTemplate;
    private final static String ES_COUNTRIES = "https://restcountries.eu/rest/v2/lang/es";

    public CountryRestAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        return Optional.ofNullable(restTemplate.getForEntity(URI.create(ES_COUNTRIES), Country[].class).getBody())
                .map(List::of)
                .orElse(List.of());
    }
}
