package com.pingStream.pingStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PingStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(PingStreamApplication.class, args);
	}

}
