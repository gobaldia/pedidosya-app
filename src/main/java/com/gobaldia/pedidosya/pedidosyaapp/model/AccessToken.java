package com.gobaldia.pedidosya.pedidosyaapp.model;

public class AccessToken {
    private String access_token;

    public AccessToken(String access_token) {
        this.access_token = access_token;
    }

    public AccessToken() {
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }
}
