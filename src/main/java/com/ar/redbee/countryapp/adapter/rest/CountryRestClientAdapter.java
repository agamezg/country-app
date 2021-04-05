package com.ar.redbee.countryapp.adapter.rest;

import com.ar.redbee.countryapp.adapter.rest.mapper.CountryRestMapper;
import com.ar.redbee.countryapp.adapter.rest.model.CountryRestModel;
import com.ar.redbee.countryapp.application.port.out.CountryRepository;
import com.ar.redbee.countryapp.domain.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
        var result = restTemplate.exchange(ES_COUNTRIES,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CountryRestModel>>() {
                });

        return  Optional.ofNullable(result.getBody())
                .map(CountryRestMapper::toDomain)
                .orElseThrow();
    }
}
