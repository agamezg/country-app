package com.ar.redbee.countryapp.util;

import com.ar.redbee.countryapp.domain.Country;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class StubsFactory {

    public static List<Country> buildCountriesList() {
        return List.of(
                Country.builder()
                        .name("Argentina")
                        .alpha2Code("AR")
                        .alpha3Code("ARG")
                        .capital("Buenos Aires")
                        .build(),
                Country.builder()
                        .name("Belize")
                        .alpha2Code("BZ")
                        .alpha3Code("BLZ")
                        .capital("Belmopan")
                        .build(),
                Country.builder()
                        .name("Bolivia (Plurinational State of)")
                        .alpha2Code("BO")
                        .alpha3Code("BOL")
                        .capital("Sucre")
                        .build(),
                Country.builder()
                        .name("Chile")
                        .alpha2Code("CL")
                        .alpha3Code("CHL")
                        .capital("Santiago")
                        .build(),
                Country.builder()
                        .name("Colombia")
                        .alpha2Code("CO")
                        .alpha3Code("COL")
                        .capital("Bogotá")
                        .build(),
                Country.builder()
                        .name("Costa Rica")
                        .alpha2Code("CR")
                        .alpha3Code("CRI")
                        .capital("San José")
                        .build(),
                Country.builder()
                        .name("Cuba")
                        .alpha2Code("CU")
                        .alpha3Code("CUB")
                        .capital("Havana")
                        .build()
        );
    }

    public static String loadJson(String path) throws IOException {
        final var resourceLoader = new DefaultResourceLoader();
        final var resource = resourceLoader.getResource(path);
        final var reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name());
        return FileCopyUtils.copyToString(reader);
    }
}
