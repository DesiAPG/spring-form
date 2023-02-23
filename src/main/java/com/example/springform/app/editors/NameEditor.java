package com.example.springform.app.editors;

import java.beans.PropertyEditorSupport;

public class NameEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim());
    }
}
