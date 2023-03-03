package com.example.springform.app.Controller;

import com.example.springform.app.editors.CountryPropertyEditor;
import com.example.springform.app.editors.NameEditor;
import com.example.springform.app.models.domain.Country;
import com.example.springform.app.models.domain.User;
import com.example.springform.app.services.CountryService;
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

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryPropertyEditor countryPropertyEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "birtDate", new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(String.class, "name", new NameEditor());
        binder.registerCustomEditor(Country.class, "country", countryPropertyEditor);
    }

    @ModelAttribute("country")
    public List<String> countries() {
        return Arrays.asList("Spain", "Mexico", "Chile", "Argentina", "Peru", "Colombia", "Venezuela");
    }

    @ModelAttribute("countryList")
    public List<Country> countryList() {
        return countryService.listCountries();
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
    public String processForm(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Result of form");
            return "form";
        }
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String result(@SessionAttribute(name = "user", required = false) User user, Model model, SessionStatus status) {
        if (user == null) {
            return "redirect:/form";
        }
        model.addAttribute("title", "Result of form");
        status.setComplete();
        return "result";
    }
}
