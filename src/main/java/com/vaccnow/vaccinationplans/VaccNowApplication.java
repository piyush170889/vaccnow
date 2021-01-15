package com.vaccnow.vaccinationplans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vaccnow.vaccinationplans"})
public class VaccNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccNowApplication.class, args);
	}

}
