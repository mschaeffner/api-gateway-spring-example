package org.mschaeffner.apigateway;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiGatewaySpringExampleApplication.class)
@WebAppConfiguration

public class ApiGatewaySpringExampleApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
		// do nothing, just to test that the application context loads
	}

}
