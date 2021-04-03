package com.ar.redbee.countryapp.controller;

import com.ar.redbee.countryapp.domain.Country;
import com.ar.redbee.countryapp.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    ResponseEntity<List<Country>> getCountries(){
        log.info("Llamando al endpoint GET -> /countries");
        final var countries =  countryService.getCountries();
        log.info("Países encontrados: {}", countries.size());
        return ResponseEntity.ok(countries);
    }
}
