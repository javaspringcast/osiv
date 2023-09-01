package com.nuagike.demo.osiv;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuagike.demo.osiv.dto.OrderDTO;
import com.nuagike.demo.osiv.dto.ProductDTO;
import com.nuagike.demo.osiv.service.ProductService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OsivApplicationTests {

	@Autowired
	ProductService productService;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		productService.addProduct(ProductDTO.builder().name("product 1").price(10L).build());
		productService.addProduct(ProductDTO.builder().name("product 2").price(20L).build());
		productService.addProduct(ProductDTO.builder().name("product 3").price(30L).build());
	}

	@Test
	void orderAdded() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/orders")//
				.content(new ObjectMapper().writeValueAsString(//
						OrderDTO.builder()//
								.products(Set.of(//
										ProductDTO.builder()//
												.name("product 3")//
												.build(), //
										ProductDTO.builder()//
												.name("product 1")//
												.build()))//
								.build()))//
				.contentType(MediaType.APPLICATION_JSON)//
				.accept(MediaType.APPLICATION_JSON))//
				.andExpect(MockMvcResultMatchers.status().isCreated())//
				.andDo(MockMvcResultHandlers.print()).andReturn();
		
	}

}
