package com.gobaldia.pedidosya.pedidosyaapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gobaldia.pedidosya.pedidosyaapp.security.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    @Value("${pedidosya.api.endpoint}")
    private static String pedidosYaApiEndpoint = "http://stg-api.pedidosya.com/public/v1";

    @RequestMapping("/home")
    public ModelAndView home(Model model,
                             HttpServletRequest request,
                             @CookieValue(value = "userToken", required = false) String userToken) {
        if (!userIsLogged(userToken)) {
            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("index");
    }

    @RequestMapping(value = "/get-restaurants", produces = {"application/json"}, method = GET)
    ResponseEntity<?> getRestaurantsByPass(@CookieValue(value = "userToken", required = false) String userToken,
                                           HttpServletRequest request) {
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        JsonNode restaurants;
        if (lat != null && lng != null) {
            restaurants = getRestaurants(userToken, String.format("%s,%s", lat, lng));
        }
        else {
            restaurants = getRestaurants(userToken, "-34.892349,-56.160892");
        }
        return ResponseEntity.ok(restaurants);
    }


    public JsonNode getRestaurants(String userToken, String coordinates) {
        RestTemplate restTemplate = new RestTemplate();
        String getRestaurantsUri = String.format("%s/search/restaurants?country=%s&point=%s&max=20&opened=1&fields=name,topCategories,ratingScore,logo,deliveryTimeMaxMinutes,link,coordinates", pedidosYaApiEndpoint, 1, coordinates);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", userToken);
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> exchange;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            exchange = restTemplate.exchange(getRestaurantsUri, HttpMethod.GET, entity, String.class);
            JsonNode restaurants = objectMapper.readValue(exchange.getBody(), JsonNode.class);
            return restaurants;
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining restaurants");
        }
        return null;
    }

    private boolean userIsLogged(String userToken) {
        return userToken != null;
    }
}
