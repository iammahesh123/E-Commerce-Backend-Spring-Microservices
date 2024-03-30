package org.example.eurekaserverservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerServiceApplication.class, args);
	}

}
