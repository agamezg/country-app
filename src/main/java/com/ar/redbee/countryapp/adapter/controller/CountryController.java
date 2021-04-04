package com.ar.redbee.countryapp.adapter.controller;

import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.application.port.in.GetCountriesQuery;
import lombok.extern.slf4j.Slf4j;
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

    public CountryController(GetCountriesQuery getCountriesQuery) {

        this.getCountriesQuery = getCountriesQuery;
    }

    @GetMapping("/countries")
    ResponseEntity<List<Country>> getCountries() {
        log.info("Calling GET -> /countries");
        final var countries = getCountriesQuery.execute();
        log.info("Countries found: {}", countries.size());
        return ResponseEntity.ok(countries);
    }
}
