package com.gobaldia.pedidosya.pedidosyaapp.controller;

import com.gobaldia.pedidosya.pedidosyaapp.model.User;
import com.gobaldia.pedidosya.pedidosyaapp.model.UserLogin;
import com.gobaldia.pedidosya.pedidosyaapp.security.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(Model model) {

        model.addAttribute("userLogin", new UserLogin());
        return new ModelAndView("login");
    }

    @RequestMapping("/dologin")
    public ModelAndView doLogin(Model model,
                                HttpServletResponse response,
                                @ModelAttribute(value = "userLogin") UserLogin userLogin) {

        User loggedUser = Login.login(userLogin.getEmail(), userLogin.getPassword(), response);

        if (loggedUser.getId() != null) {
            String loggedUserName = loggedUser.getName();
            String loggedUserLastName = loggedUser.getLastName();
            response.addCookie(new Cookie("loggedUserName", loggedUserName));
            response.addCookie(new Cookie("loggedUserLastName", loggedUserLastName));

            return new ModelAndView("redirect:/home");
        }
        else {
            model.addAttribute("loginError", "Email y/o contraseña incorrectos");

            return new ModelAndView("/login");
        }

    }
}
