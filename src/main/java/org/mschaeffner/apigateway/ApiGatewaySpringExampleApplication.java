package org.mschaeffner.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApiGatewaySpringExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewaySpringExampleApplication.class, args);
	}

}
