package com.example.kotryn;

import com.example.kotryn.processes.AbstractProcessFactory;
import com.example.kotryn.processes.ProcessFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KotrynApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotrynApplication.class, args);
	}
}
