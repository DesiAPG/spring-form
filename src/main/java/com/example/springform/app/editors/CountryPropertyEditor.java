package com.example.springform.app.editors;

import com.example.springform.app.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CountryService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        Integer id = Integer.parseInt(idString);
        this.setValue(service.getId(id));
    }
}
