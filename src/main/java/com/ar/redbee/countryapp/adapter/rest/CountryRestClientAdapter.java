package com.ar.redbee.countryapp.adapter.rest;

import com.ar.redbee.countryapp.adapter.rest.model.CountryApiModel;
import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import com.ar.redbee.countryapp.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class CountryRestClientAdapter implements CountryRepository {

    private final RestTemplate restTemplate;
    private final static String ES_COUNTRIES = "https://restcountries.eu/rest/v2/lang/es";

    public CountryRestClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Country> getCountries() {
        log.info("Calling country api: {}", ES_COUNTRIES);
        return Optional.ofNullable(restTemplate.getForObject(ES_COUNTRIES, CountryApiModel[].class))
                .map(List::of)
                .orElse(List.of())
                .stream()
                .map(CountryApiModel::toDomain)
                .collect(Collectors.toList());
    }
}
