package com.gobaldia.pedidosya.pedidosyaapp.controller;

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
}
