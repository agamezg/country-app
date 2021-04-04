package com.ar.redbee.countryapp.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Country {
    String name;
    String alpha2Code;
    String alpha3Code;
    String capital;
}
