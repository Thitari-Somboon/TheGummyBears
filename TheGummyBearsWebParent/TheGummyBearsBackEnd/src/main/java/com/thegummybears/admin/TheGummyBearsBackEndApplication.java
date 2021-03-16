package com.thegummybears.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.thegummybears.common.entity", "com.thegummybears.admin.user"})
public class TheGummyBearsBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheGummyBearsBackEndApplication.class, args);
	}

}
