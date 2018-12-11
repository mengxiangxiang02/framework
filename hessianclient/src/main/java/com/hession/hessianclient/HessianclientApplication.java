package com.hession.hessianclient;

import com.hession.service.HelloWorldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@SpringBootApplication
public class HessianclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HessianclientApplication.class, args);
	}

	@Bean
	public HessianProxyFactoryBean helloClient() {
		HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
		factory.setServiceUrl("http://localhost:8092/HelloWorldService");
		factory.setServiceInterface(HelloWorldService.class);
		return factory;
	}
}
