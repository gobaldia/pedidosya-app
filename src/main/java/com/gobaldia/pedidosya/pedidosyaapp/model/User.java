package com.gobaldia.pedidosya.pedidosyaapp.model;

public class User {
    private Long id;
    private String lastName;
    private String name;
    private Country country;

    public User(Long id, String lastName, String name, Country country) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.country = country;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
