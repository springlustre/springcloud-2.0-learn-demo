package com.springlustre.tech.consuldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulDemoConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulDemoConsumerApplication.class, args);
	}
}
