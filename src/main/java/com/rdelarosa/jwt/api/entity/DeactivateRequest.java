package com.rdelarosa.jwt.api.entity;

public class DeactivateRequest {

    private String email;

    public DeactivateRequest() {
    }

    public DeactivateRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
