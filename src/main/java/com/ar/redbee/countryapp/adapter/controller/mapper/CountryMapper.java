package com.ar.redbee.countryapp.adapter.controller.mapper;

import com.ar.redbee.countryapp.adapter.controller.model.CountryRest;
import com.ar.redbee.countryapp.domain.Country;

import java.util.*;
import java.util.stream.Collectors;

public class CountryMapper {

    public static CountryRest of(Country country) {
        return CountryRest.builder()
                .alpha2Code(country.getAlpha2Code())
                .capital(country.getCapital())
                .alpha3Code(country.getAlpha3Code())
                .name(country.getName())
                .build();
    }

    public static List<CountryRest> of(List<Country> countryList) {
        return countryList.stream()
                .map(CountryMapper::of)
                .collect(Collectors.toList());
    }
}
