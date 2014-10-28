package ca.etsmtl.gti710.controllers;

import ca.etsmtl.gti710.exceptions.ProductNotFoundException;
import ca.etsmtl.gti710.models.Country;
import ca.etsmtl.gti710.providers.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CountriesController {

    @Autowired
    IProvider provider;

    @RequestMapping("/countries")
    public ArrayList<Country> products() {
        return provider.getCountries();
    }

    @RequestMapping("/countries/{country_id}")
    public Country product(@PathVariable("country_id") int country_id) {
        try {
            return provider.getCountry(country_id);
        } catch (NullPointerException e) {
            throw new ProductNotFoundException();
        }
    }
}
