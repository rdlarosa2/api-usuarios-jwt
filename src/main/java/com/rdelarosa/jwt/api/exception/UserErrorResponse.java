package com.rdelarosa.jwt.api.exception;

public class UserErrorResponse {

    private String mensaje;

    public UserErrorResponse() {
    }

    public UserErrorResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
