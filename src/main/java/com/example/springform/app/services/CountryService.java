package com.example.springform.app.services;

import com.example.springform.app.models.domain.Country;

import java.util.List;

public interface CountryService {
    public List<Country> listCountries();

    public Country getId(Integer id);
}
