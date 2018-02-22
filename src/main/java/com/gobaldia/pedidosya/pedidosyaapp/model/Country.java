package com.gobaldia.pedidosya.pedidosyaapp.model;

public class Country {
    private Long id;

    public Country(Long id) {
        this.id = id;
    }

    public Country() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}