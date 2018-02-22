package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.model.Country;
import com.gobaldia.pedidosya.pedidosyaapp.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void setId() {
        Long expectedValue = 1L;

        User user = new User();

        user.setId(expectedValue);

        Assert.assertEquals(user.getId(), expectedValue);
    }

    @Test
    public void setLastName() {
        String expectedValue = "Gabriel";

        User user = new User();

        user.setLastName(expectedValue);

        Assert.assertEquals(user.getLastName(), expectedValue);
    }

    @Test
    public void setName() {
        String expectedValue = "Obaldia";

        User user = new User();

        user.setName(expectedValue);

        Assert.assertEquals(user.getName(), expectedValue);
    }

    @Test
    public void setCountry() {
        Country expectedValue = new Country(1L);

        User user = new User();

        user.setCountry(expectedValue);

        Assert.assertEquals(user.getCountry(), expectedValue);
    }
}
