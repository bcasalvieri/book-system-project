package com.company.noteservive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NoteServiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteServiveApplication.class, args);
	}

}
