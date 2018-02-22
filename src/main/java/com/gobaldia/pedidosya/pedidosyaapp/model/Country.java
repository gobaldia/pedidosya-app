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

    @Override
    public boolean equals(Object obj) {
        Country toCompare = (Country) obj;
        return this.getId() == toCompare.getId();
    }
}