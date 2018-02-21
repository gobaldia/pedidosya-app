package com.gobaldia.pedidosya.pedidosyaapp;

import com.gobaldia.pedidosya.pedidosyaapp.security.Login;
import com.gobaldia.pedidosya.pedidosyaapp.security.LoginPedidosYaStg;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.gobaldia.pedidosya.pedidosyaapp.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PedidosyaAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testLogin() {
		LoginPedidosYaStg l = new LoginPedidosYaStg();
		l.login("test_automation_000@pedidosya.com", "abc1234");
		Assert.assertTrue(true);
	}

}
