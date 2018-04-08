package com.example.kotryn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KotrynApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotrynApplication.class, args);
	}

	/*@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return builder;
	}*/
}
