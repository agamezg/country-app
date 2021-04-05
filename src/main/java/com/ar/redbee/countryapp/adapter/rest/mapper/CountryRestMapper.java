package com.ar.redbee.countryapp.adapter.rest.mapper;

import com.ar.redbee.countryapp.adapter.rest.model.CountryRestModel;
import com.ar.redbee.countryapp.domain.Country;

import java.util.*;
import java.util.stream.Collectors;

public class CountryRestMapper {

    public static Country toDomain(CountryRestModel countryRestModel) {
        return Country.builder()
                .alpha2Code(countryRestModel.getAlpha2Code())
                .capital(countryRestModel.getCapital())
                .alpha3Code(countryRestModel.getAlpha3Code())
                .name(countryRestModel.getName())
                .build();
    }

    public static List<Country> toDomain(List<CountryRestModel> countryRestModels) {
        return countryRestModels.stream()
                .map(countryRestModel -> CountryRestMapper.toDomain(countryRestModel))
                .collect(Collectors.toList());
    }

}
