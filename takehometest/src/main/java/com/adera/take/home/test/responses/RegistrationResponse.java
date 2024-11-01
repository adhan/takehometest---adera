package com.adera.take.home.test.responses;

import org.json.JSONObject;
public class RegistrationResponse {

    private String httpStatus;

    public String getHttpStatus() {
        return httpStatus;
    }

    public RegistrationResponse setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    @Override
    public String toString() {
        String message;
        JSONObject json = new JSONObject();

        json.put("status", httpStatus);
        json.put("message", "Registrasi berhasil silakan login");
        json.put("data", "");

        message = json.toString();

        return message;
    }


}
