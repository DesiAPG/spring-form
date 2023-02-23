package com.example.springform.app.Controller;

import com.example.springform.app.models.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@SessionAttributes("user")
public class FormController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "birtDate", new CustomDateEditor(dateFormat, false));
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
