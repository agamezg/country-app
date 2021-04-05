package com.ar.redbee.countryapp.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Country {
    String name;
    String alpha2Code;
    String alpha3Code;
    String capital;
}
