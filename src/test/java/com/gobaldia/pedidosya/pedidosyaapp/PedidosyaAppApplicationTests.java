package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.security.Login;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidosyaAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	/*@Test
	public void testLogin() {
		Login l = new Login();
		l.login("test_automation_000@pedidosya.com", "abc1234");
		Assert.assertTrue(true);
	}*/

}
