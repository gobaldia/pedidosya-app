package com.gobaldia.pedidosya.pedidosyaapp.model;

public class AccessToken {
    private String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken() {
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
