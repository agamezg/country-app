package com.ar.redbee.countryapp.adapter.rest.model;

import com.ar.redbee.countryapp.domain.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryApiModel {
    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;

    public Country toDomain() {
        return Country.builder()
                .name(this.name)
                .alpha2Code(this.alpha2Code)
                .alpha3Code(this.alpha3Code)
                .capital(this.capital)
                .build();
    }
}
