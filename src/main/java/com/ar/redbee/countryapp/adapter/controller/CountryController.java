package com.ar.redbee.countryapp.adapter.controller;

import com.ar.redbee.countryapp.adapter.controller.mapper.CountryMapper;
import com.ar.redbee.countryapp.adapter.controller.model.CountryRest;
import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.application.port.in.GetCountriesQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1")
@Slf4j
public class CountryController {

    private final GetCountriesQuery getCountriesQuery;

    @Autowired
    public CountryController(GetCountriesQuery getCountriesQuery) {

        this.getCountriesQuery = getCountriesQuery;
    }

    @GetMapping("/countries")
    ResponseEntity<List<CountryRest>> getCountries() {

        log.info("Llamado al endpoint GET -> /countries");
        final var countries = getCountriesQuery.execute();
        log.info("Países encontrados: {}", countries.size());

        var countriesRest = CountryMapper.of(countries);

        return ResponseEntity.ok(countriesRest);
    }
}
