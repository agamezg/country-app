package com.ar.redbee.countryapp.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Country {
    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
}
