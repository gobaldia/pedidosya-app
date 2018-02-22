package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.model.UserLogin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLoginTest {

    @Test
    public void setEmail() {
        UserLogin userLogin = new UserLogin();
        String expectedValue = "g@g.com";

        userLogin.setEmail(expectedValue);

        Assert.assertEquals(userLogin.getEmail(), expectedValue);
    }

    @Test
    public void setPassword() {
        UserLogin userLogin = new UserLogin();
        String expectedValue = "p4ssw0rd";

        userLogin.setPassword(expectedValue);

        Assert.assertEquals(userLogin.getPassword(), expectedValue);
    }
}
