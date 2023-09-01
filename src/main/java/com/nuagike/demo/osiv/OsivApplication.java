package com.nuagike.demo.osiv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.nuagike.demo.osiv.dto.ProductDTO;
import com.nuagike.demo.osiv.service.ProductService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class OsivApplication {

	@Autowired
	ProductService articleService;

	public static void main(String[] args) {
		SpringApplication.run(OsivApplication.class, args);
	}

}
