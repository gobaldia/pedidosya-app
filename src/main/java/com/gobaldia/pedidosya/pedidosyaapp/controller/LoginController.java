package com.gobaldia.pedidosya.pedidosyaapp.controller;

import com.gobaldia.pedidosya.pedidosyaapp.model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(Model model) {

        model.addAttribute("userLogin", new UserLogin());
        return new ModelAndView("login");
    }

    @RequestMapping("/dologin")
    public ModelAndView doLogin(Model model, @ModelAttribute(value = "userLogin") UserLogin car) {


        return new ModelAndView("index");
    }
}
