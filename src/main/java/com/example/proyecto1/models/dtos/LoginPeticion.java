package com.example.proyecto1.models.dtos;

public class LoginPeticion {
    private String email;
    private String password;

    public LoginPeticion() {
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
