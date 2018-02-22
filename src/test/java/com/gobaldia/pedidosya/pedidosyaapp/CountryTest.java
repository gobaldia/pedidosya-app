package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.model.Country;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryTest {

    @Test
    public void setId() {
        Long expectedValue = 1L;

        Country country = new Country(0L);

        country.setId(expectedValue);

        Assert.assertEquals(country.getId(), expectedValue);
    }

    @Test
    public void areEqual() {
        Country c1 = new Country(1L);
        Country c2 = new Country(1L);

        boolean comparisson = c1.equals(c2);

        Assert.assertTrue(comparisson);
    }

    @Test
    public void areNotEqual() {
        Country c1 = new Country(1L);
        Country c2 = new Country(2L);

        boolean comparisson = c1.equals(c2);

        Assert.assertFalse(comparisson);
    }
}
