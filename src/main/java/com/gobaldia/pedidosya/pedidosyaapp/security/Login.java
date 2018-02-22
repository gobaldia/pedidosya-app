package com.gobaldia.pedidosya.pedidosyaapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gobaldia.pedidosya.pedidosyaapp.model.AccessToken;
import com.gobaldia.pedidosya.pedidosyaapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
public class Login {

    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    @Value("${pedidosya.api.endpoint}")
    private static String pedidosYaApiEndpoint = "http://stg-api.pedidosya.com/public/v1";

    @Value("${pedidosya.client.id}")
    private static String clientId = "test";

    @Value("${pedidosya.client.secret}")
    private static String clientSecret = "PeY@@Tr1v1@943";

    public Login() {
    }

    public static User login(String email, String password, HttpServletResponse response) {
        String appToken = getAppToken();
        String userToken = getUserToken(appToken, email, password);
        response.addCookie(new Cookie("userToken", userToken));
        User user = getUserInfo(userToken);
        return user;
    }

    private static String getAppToken() {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/tokens?clientId=%s&clientSecret=%s", pedidosYaApiEndpoint, clientId, clientSecret);
        AccessToken appToken = new AccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> exchange;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, String.class);
            AccessToken accessToken = objectMapper.readValue(exchange.getBody(), AccessToken.class);
            return accessToken.getAccess_token();
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new String();
    }

    private static String getUserToken(String appToken, String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/tokens?userName=%s&password=%s", pedidosYaApiEndpoint, email, password);
        AccessToken userToken = new AccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("Authorization", appToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> exchange;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, String.class);
            AccessToken accessToken = objectMapper.readValue(exchange.getBody(), AccessToken.class);
            return accessToken.getAccess_token();
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new String();
    }

    private static User getUserInfo(String userToken) {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/myAccount", pedidosYaApiEndpoint);
        User user = new User();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("Authorization", userToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> exchange;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, String.class);
            user = objectMapper.readValue(exchange.getBody(), User.class);
            return user;
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new User();
    }

}
