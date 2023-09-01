package com.nuagike.demo.osiv.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Long id;

	private String reference;

	private Set<ProductDTO> products;

	private InvoiceDTO invoice;
}
