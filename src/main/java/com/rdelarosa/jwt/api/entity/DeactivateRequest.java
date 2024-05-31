package com.rdelarosa.jwt.api.entity;

public class DeactivateRequest {

    private Integer id;

    public DeactivateRequest() {
    }

    public DeactivateRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
