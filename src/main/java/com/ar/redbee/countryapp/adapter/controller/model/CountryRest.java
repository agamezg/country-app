package com.ar.redbee.countryapp.adapter.controller.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CountryRest {
    String name;
    String alpha2Code;
    String alpha3Code;
    String capital;

}
