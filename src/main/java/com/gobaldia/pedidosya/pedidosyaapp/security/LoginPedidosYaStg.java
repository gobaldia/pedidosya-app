package com.gobaldia.pedidosya.pedidosyaapp.security;

import com.gobaldia.pedidosya.pedidosyaapp.model.AccessToken;
import com.gobaldia.pedidosya.pedidosyaapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class LoginPedidosYaStg implements Login {

    private static final Logger logger = LoggerFactory.getLogger(LoginPedidosYaStg.class);

    @Value("${pedidosya.api.endpoint}")
    private String pedidosYaApiEndpoint;

    @Value("${pedidosya.client.id}")
    private String clientId;

    @Value("${pedidosya.client.secret}")
    private String clientSecret;

    @Override
    public User login(String email, String password) {
        String appToken = getAppToken();
        String userToken = getUserToken(appToken, email, password);
        User user = getUserInfo(userToken);
        return user;
    }

    private String getAppToken() {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/tokens?clientId=%s&clientSecret=%s", pedidosYaApiEndpoint, clientId, clientSecret);
        AccessToken appToken = new AccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<AccessToken> exchange;
        try {
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, AccessToken.class);
            return exchange.getBody().getAccessToken();
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new String();
    }

    private String getUserToken(String appToken, String email, String password) {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/tokens?username=%s&password=%s", pedidosYaApiEndpoint, email, password);
        AccessToken userToken = new AccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("Authorization", appToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<AccessToken> exchange;
        try {
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, AccessToken.class);
            return exchange.getBody().getAccessToken();
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new String();
    }

    private User getUserInfo(String userToken) {
        RestTemplate restTemplate = new RestTemplate();
        String getAppTokenUri = String.format("%s/myAccout", pedidosYaApiEndpoint);
        User user = new User();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add("Authorization", userToken);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<User> exchange;
        try {
            exchange = restTemplate.exchange(getAppTokenUri, HttpMethod.GET, entity, User.class);
            return exchange.getBody();
        }
        catch (Exception e) {
            logger.warn("An error ocurred while obtaining App token");
            // FIXME: throw 401
        }
        return new User();
    }
}
