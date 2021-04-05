package com.ar.redbee.countryapp.adapter.rest.model;

import com.ar.redbee.countryapp.domain.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryRestModel {
    String name;
    String alpha2Code;
    String alpha3Code;
    String capital;

}
