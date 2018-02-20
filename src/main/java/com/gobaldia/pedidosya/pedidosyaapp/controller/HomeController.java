package com.gobaldia.pedidosya.pedidosyaapp.controller;

import com.gobaldia.pedidosya.pedidosyaapp.model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(Model model) {

        model.addAttribute("userLogin", new UserLogin());
        return new ModelAndView("login");
    }
}
