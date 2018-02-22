package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.model.AccessToken;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccessTokenTest {
    @Test
    public void setAccessToken() {
        String expectedValue = "at";

        AccessToken accessToken = new AccessToken("someAccessToken");
        accessToken.setAccess_token(expectedValue);

        Assert.assertEquals(accessToken.getAccess_token(), expectedValue);
    }
}
