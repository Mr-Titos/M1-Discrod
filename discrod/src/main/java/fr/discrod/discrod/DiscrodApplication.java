package fr.discrod.discrod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class DiscrodApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscrodApplication.class, args);
	}

}
