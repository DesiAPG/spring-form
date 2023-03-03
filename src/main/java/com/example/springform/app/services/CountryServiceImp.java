package com.example.springform.app.services;


import com.example.springform.app.models.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    private List<Country> list;

    public CountryServiceImp() {
        this.list = Arrays.asList(
                new Country(1, "ES", "Spain"),
                new Country(2, "MX", "Mexico"),
                new Country(3, "CL", "Chile"),
                new Country(4, "AR", "Argentina"),
                new Country(5, "PE", "Peru"),
                new Country(6, "CO", "Colombia"),
                new Country(7, "VE", "Venezuela"));
    }

    @Override
    public List<Country> listCountries() {
        return list;
    }

    @Override
    public Country getId(Integer id) {
        Country result = null;
        for (Country country : this.list) {
            if (id == country.getId()) {
                result = country;
                break;
            }
        }
        return null;
    }
}
