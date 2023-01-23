package com.kojelauta.projekti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "classpath:application-${envTarget:local}.properties" })
public class KojelautaApplication {
	public static void main(String[] args) {
		SpringApplication.run(KojelautaApplication.class, args);
	}

}
