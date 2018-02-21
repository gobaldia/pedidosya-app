package com.gobaldia.pedidosya.pedidosyaapp.security;

import com.gobaldia.pedidosya.pedidosyaapp.model.User;

public interface Login {
    User login(String email, String password);
}
