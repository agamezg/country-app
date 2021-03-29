package com.ar.redbee.countryapp.repository;

import com.ar.redbee.countryapp.domain.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository {

    private final RestTemplate restTemplate;
    private final static String ES_COUNTRIES = "https://restcountries.eu/rest/v2/lang/es";

    public CountryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Country> getCountries() {
        return Optional.ofNullable(restTemplate.getForEntity(URI.create(ES_COUNTRIES), Country[].class).getBody())
                .map(List::of)
                .orElse(List.of());
    }
}
