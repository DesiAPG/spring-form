package com.example.springform.app.Controller;

import com.example.springform.app.editors.NameEditor;
import com.example.springform.app.models.domain.Country;
import com.example.springform.app.models.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@SessionAttributes("user")
public class FormController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "birtDate", new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, "name", new NameEditor());
    }

    @ModelAttribute("country")
    public List<String> countries() {
        return Arrays.asList("Spain", "Mexico", "Chile", "Argentina", "Peru", "Colombia", "Venezuela");
    }

    @ModelAttribute("countryList")
    public List<Country> countryList() {
        return Arrays.asList(
                new Country(1, "ES", "Spain"),
                new Country(2, "MX", "Mexico"),
                new Country(3, "CL", "Chile"),
                new Country(4, "AR", "Argentina"),
                new Country(5, "PE", "Peru"),
                new Country(6, "CO", "Colombia"),
                new Country(7, "VE", "Venezuela"));
    }

    @ModelAttribute("countryMap")
    public Map<String, String> countriesMap() {
        Map<String, String> countries = new HashMap<String, String>();
        countries.put("ES", "Spain");
        countries.put("MX", "Mexico");
        countries.put("CL", "Chile");
        countries.put("AR", "Argentina");
        countries.put("PE", "Peru");
        countries.put("CO", "Colombia");
        countries.put("VE", "Venezuela");
        return countries;
    }

    @GetMapping("/form")
    public String form(Model model) {
        User user = new User();
        user.setName("Duvitas");
        user.setSurname("Marin");
        user.setId("1");
        model.addAttribute("user", user);
        model.addAttribute("title", "Sign in");
        return "form";
    }

    @PostMapping("/form")
    public String processForm(@Valid User user, BindingResult result, Model model, SessionStatus status) {
        model.addAttribute("title", "Result of form");

        if (result.hasErrors()) {
            return "form";
        }

        model.addAttribute("user", user);
        status.setComplete();
        return "result";
    }

}
