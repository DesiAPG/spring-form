package com.example.springform.app.models.domain;

import com.example.springform.app.validation.RegexIdentificator;
import jakarta.validation.constraints.*;

public class User {

    @RegexIdentificator()
    private String id;
    @NotBlank
    @Size(min = 3, max = 10)
    private String username;
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Min(5)
    @Max(100)
    private Integer account;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
